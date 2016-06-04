package com.example.asus.alteracionesdentales;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Medio extends ActionBarActivity implements Runnable {

    private TextView lblPuntaje, lblFallas;
    private int puntaje, fallas, cont = 0;
    private ImageButton img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, auxiliar;
    private Button botonSalir, botonReiniciar;
    private int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private ImageButton imgBotones[];
    private int valorSeleccionado = -1;
    private int valorBorrar = 0;
    private Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta0);
            imgBotones[valorSeleccionado].setImageBitmap(bpm);
            valorSeleccionado = -1;
            imgBotones[valorBorrar].setImageBitmap(bpm);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(this, "MEDIO", Toast.LENGTH_SHORT).show();
        puntaje = 0;
        fallas = 0;
        setContentView(R.layout.medio);
        iniciarCartas();
        imgBotones = new ImageButton[]{img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18};
        desordenarCartas();
        botonSalir = (Button) findViewById(R.id.btnSalir);
        botonReiniciar = (Button) findViewById(R.id.btnReiniciar);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntaje = 0;
                fallas = 0;
                cont=0;
                valorSeleccionado = -1;
                Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta0);
                for (ImageButton img : imgBotones) {
                    img.setEnabled(true);
                    img.setImageBitmap(bpm);
                }
                lblPuntaje.setText(puntaje + " ");
                lblFallas.setText(fallas + " ");
                desordenarCartas();
            }
        });
        agregarEventosCartas();
        lblPuntaje = (TextView) findViewById(R.id.txtPuntaje);
        lblFallas = (TextView) findViewById(R.id.txtFallas);
    }

    private void desordenarCartas() {
        Random rnd = new Random();
        int aux;
        int indiceAux;
        for (int i = 0; i < valores.length; i++) {
            aux = valores[i];
            indiceAux = rnd.nextInt(9);
            valores[i] = valores[indiceAux];
            valores[indiceAux] = aux;
        }
    }

    private void controlador(int opcion, ImageButton img) {
        Bitmap bpm = null;
        opcion--;
        switch (valores[opcion]) {
            case 1:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta1);
                break;
            case 2:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta2);
                break;
            case 3:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta3);
                break;
            case 4:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta4);
                break;
            case 5:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta5);
                break;
            case 6:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta6);
                break;
            case 7:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta7);
                break;
            case 8:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta8);
                break;
            case 9:
                bpm = BitmapFactory.decodeResource(getResources(), R.drawable.carta9);
                break;
        }
        if (valorSeleccionado == -1) {
            valorSeleccionado = opcion;
            auxiliar = img;
            img.setImageBitmap(bpm);
            img.setEnabled(false);
        }
        else {
            if (valores[valorSeleccionado] == valores[opcion]) {
                if(cont >= 0 && cont < 9)
                    cont++;
                if(cont == 9)
                    Toast.makeText(this, "¡¡FIN DEL JUEGO!!", Toast.LENGTH_SHORT).show();
                puntaje += 10;
                lblPuntaje.setText(puntaje + " ");
                if(cont != 9)
                    Toast.makeText(this, "¡Acertaste!", Toast.LENGTH_SHORT).show();
                img.setImageBitmap(bpm);
                img.setEnabled(false);
                valorSeleccionado = -1;
            }
            else {
                if (puntaje > 0)
                    puntaje -= 1;
                lblPuntaje.setText(puntaje + " ");
                fallas++;
                lblFallas.setText(fallas + " ");
                valorBorrar = opcion;
                img.setImageBitmap(bpm);
                auxiliar.setEnabled(true);
                Thread hilo = new Thread(this);
                hilo.start();
            }
        }
    }

    private void iniciarCartas() {
        img1 = (ImageButton) findViewById(R.id.carta1);
        img2 = (ImageButton) findViewById(R.id.carta2);
        img3 = (ImageButton) findViewById(R.id.carta3);
        img4 = (ImageButton) findViewById(R.id.carta4);
        img5 = (ImageButton) findViewById(R.id.carta5);
        img6 = (ImageButton) findViewById(R.id.carta6);
        img7 = (ImageButton) findViewById(R.id.carta7);
        img8 = (ImageButton) findViewById(R.id.carta8);
        img9 = (ImageButton) findViewById(R.id.carta9);
        img10 = (ImageButton) findViewById(R.id.carta10);
        img11 = (ImageButton) findViewById(R.id.carta11);
        img12 = (ImageButton) findViewById(R.id.carta12);
        img13 = (ImageButton) findViewById(R.id.carta13);
        img14 = (ImageButton) findViewById(R.id.carta14);
        img15 = (ImageButton) findViewById(R.id.carta15);
        img16 = (ImageButton) findViewById(R.id.carta16);
        img17 = (ImageButton) findViewById(R.id.carta17);
        img18 = (ImageButton) findViewById(R.id.carta18);
    }

    private void agregarEventosCartas() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(1, img1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(2, img2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(3, img3);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(4, img4);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(5, img5);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(6, img6);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(7, img7);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(8, img8);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(9, img9);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(10, img10);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(11, img11);
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(12, img12);
            }
        });
        img13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(13, img13);
            }
        });
        img14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(14, img14);
            }
        });
        img15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(15, img15);
            }
        });
        img16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(16, img16);
            }
        });
        img17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(17, img17);
            }
        });
        img18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(18, img18);
            }
        });
    }

    @Override
    public void run() {
        SystemClock.sleep(100);
        Message msg = new Message();
        msg.obj = 18;
        puente.sendMessage(msg);
    }
}
