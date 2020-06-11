package com.example.examenesther;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    MyList lista;
    MyModel modelo;
    MyAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list_view);
        myList.setOnCreateContextMenuListener(this);
        llenarLista();
    }
    void llenarLista(){
        lista = new MyList();

        modelo =new MyModel("Esther","Duarte","121-161097-1001Q","Administrador","Recursos Humanos", "85411756", R.drawable.empleado1);
        lista.add(modelo);

        modelo =new MyModel("Selena","Obando","121-260599-1000B","Directora","Departamento tecnología", "58852312", R.drawable.empleado2);
        lista.add(modelo);

        modelo =new MyModel("Ingrid","Fonseca","121-191298-0001W","Responsable","Aduanas", "86124513", R.drawable.empleado3);
        lista.add(modelo);

        adaptador =new MyAdapter(this,R.layout.item_list, lista);
        myList.setAdapter(adaptador);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menú");
        getMenuInflater().inflate(R.menu.menu, menu);//infla xml con los datos
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.Salir:
                this.finish();
                break;

            case R.id.Agregar: //Acción para item agregar del menú
                final Dialog dlg = new Dialog(this);//definicion objeto dialogo
                dlg.setContentView(R.layout.agregar_nuevo);//xml dialogo
                dlg.setTitle("Nuevo Empleado");
                dlg.setCancelable(false);//no puede cerrarse
                //referenciando botones del dialogo
                Button buttonAgregar = (Button) dlg.findViewById(R.id.btAgregar);
                Button buttonCancelar = (Button) dlg.findViewById(R.id.btCancelar);

                buttonAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        EditText edNombre = (EditText) dlg.findViewById(R.id.editText_Nombre);
                        EditText edApellido = (EditText) dlg.findViewById(R.id.editText_Apellidos);
                        EditText edIdentificacion = (EditText) dlg.findViewById(R.id.editText_Identificacion);
                        EditText edCargo = (EditText) dlg.findViewById(R.id.editText_Cargo);
                        EditText edArea = (EditText) dlg.findViewById(R.id.editText_Area);
                        EditText edTelefono = (EditText) dlg.findViewById(R.id.editText_Telefono);

                        if (edNombre.getText().toString().isEmpty()
                                || edApellido.getText().toString().isEmpty()
                                || edIdentificacion.getText().toString().isEmpty()
                                || edCargo.getText().toString().isEmpty()
                                || edArea.getText().toString().isEmpty()
                                || edTelefono.getText().toString().isEmpty()
                        ) {
                            Toast.makeText(MainActivity.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                        } else {
                            MyModel empleado = new MyModel();
                            empleado.setNombre(edNombre.getText().toString());
                            empleado.setApellidos(edApellido.getText().toString());
                            empleado.setIdentificacion(edIdentificacion.getText().toString());
                            empleado.setCargo(edCargo.getText().toString());
                            empleado.setArea(edArea.getText().toString());
                            empleado.setTelefono(edTelefono.getText().toString());
                            empleado.setImagen(R.drawable.empleado4);

                            lista.add(empleado);
                            adaptador.notifyDataSetChanged();

                            edNombre.setText("");
                            edApellido.setText("");
                            edIdentificacion.setText("");
                            edCargo.setText("");
                            edArea.setText("");
                            edTelefono.setText("");
                            dlg.cancel();//cierra el dialogo
                        }//cierre else
                    }

                });

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
                dlg.show(); //muestra dialogo
                break;
            case R.id.Eliminar: //acciones para item eliminar del menú
                this.lista.remove(info.position);//quita un elemento de la lista
                this.adaptador.notifyDataSetChanged();//actualiza el adaptador
                break;
        }

        return super.onContextItemSelected(item);
    }
}
