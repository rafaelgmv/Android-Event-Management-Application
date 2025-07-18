package com.example.eventmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder>{

    private ArrayList<Evento> eventos;
    private EventoClickListener eventoClickListener;



    public EventoAdapter(ArrayList<Evento> eventos,EventoClickListener eventoClickListener) {
        this.eventos = eventos;
        this.eventoClickListener = eventoClickListener;
    }

    // Cria novas views
    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_evento, parent, false);
        return new EventoViewHolder(view);
    }

    public void atualizarLista(ArrayList<Evento> eventosFiltrados) {
        this.eventos = eventosFiltrados;
        notifyDataSetChanged();
    }

    // Substitui o conteudo das views
    @Override
    public void onBindViewHolder(EventoViewHolder holder, int position) {

        Evento evento = eventos.get(position);
        switch (evento.getTipo()){
            case "Culturais":
                holder.imgTipo.setImageResource(R.drawable.baseline_theater_comedy_24);
                break;
            case "Profissionais":
                holder.imgTipo.setImageResource(R.drawable.baseline_workspace_premium_24);
                break;
            case "Desportivos":
                holder.imgTipo.setImageResource(R.drawable.baseline_sports_24);
                break;
            case "Académicos":
                holder.imgTipo.setImageResource(R.drawable.baseline_school_24);
                break;
            case "Gastronómicos":
                holder.imgTipo.setImageResource(R.drawable.baseline_restaurant_24);
                break;
        }
        holder.txtTipo.setText(evento.getTipo());
        holder.txtDescricao.setText(evento.getDescricao());
        holder.txtLocal.setText(evento.getLocal());
        holder.txtData.setText(evento.getDataString(evento.getDataEvento()));
        holder.txtHora.setText(evento.getHoraString(evento.getHoraEvento()));
        holder.imgData.setImageResource(R.drawable.baseline_calendar_today_24);
        holder.imgHora.setImageResource(R.drawable.baseline_timer_24);

        if(evento.getComprou()){
            holder.llPrecoLista.setVisibility(View.GONE);
            holder.btnCancelarCompra.setText("Cancelar compra");
            holder.btnCancelarCompra.setVisibility(View.VISIBLE);

        }else {
            if(evento.getGratis() || evento.getPreco() == 0){
                holder.imgPreco.setImageResource(R.drawable.baseline_money_off_24);
            }else {
                holder.imgPreco.setImageResource(R.drawable.baseline_attach_money_24);
            }
            holder.txtPreco.setText(String.valueOf(evento.getPreco()));
            holder.btnCancelarCompra.setText("Comprar bilhete");
            holder.btnCancelarCompra.setVisibility(View.VISIBLE);
            holder.llPrecoLista.setVisibility(View.VISIBLE);
        }

    }

    // Retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return eventos.size();
    }




    public class EventoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTipo,txtLocal, txtDescricao, txtData, txtHora, txtPreco;
        ImageView imgTipo,imgPreco, imgData, imgHora;
        Button btnCancelarCompra;
        LinearLayout llPrecoLista;

        public EventoViewHolder(View view) {
            super(view);
            imgTipo = view.findViewById(R.id.imgTipoLista);
            txtTipo = view.findViewById(R.id.txtTipoLista);
            txtDescricao = view.findViewById(R.id.txtDescricaoLista);
            txtLocal = view.findViewById(R.id.txtLocalLista);
            txtData = view.findViewById(R.id.txtDataLista);
            txtHora = view.findViewById(R.id.txtHoraLista);
            imgPreco = view.findViewById(R.id.imgPrecoLista);
            imgData = view.findViewById(R.id.imgDataLista);
            imgHora = view.findViewById(R.id.imgHoraLista);
            txtPreco = view.findViewById(R.id.txtPrecoLista);
            btnCancelarCompra = view.findViewById(R.id.btnCancelarCompra);
            llPrecoLista = view.findViewById(R.id.llPrecoLista);
            view.setOnClickListener(this);

            btnCancelarCompra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventoClickListener.onAcaoInscricao(getAdapterPosition(), btnCancelarCompra.getText().toString());
                }
            });
        }

        @Override
        public void onClick(View v) {
            eventoClickListener.onClick(getAdapterPosition());
        }



    }

    public interface EventoClickListener {
        void onClick(int position);
        void onAcaoInscricao(int position, String acao);
    }

}
