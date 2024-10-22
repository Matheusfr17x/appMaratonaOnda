package com.example.apponda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class DoacaoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Doacao> listaDoacoes;

    public DoacaoAdapter(Context context, ArrayList<Doacao> listaDoacoes) {
        this.context = context;
        this.listaDoacoes = listaDoacoes;
    }

    @Override
    public int getCount() {
        return listaDoacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDoacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doacao, parent, false);
        }

        Doacao doacao = (Doacao) getItem(position);

        ImageView imgProduto = convertView.findViewById(R.id.img_produto);
        TextView tvNomeProduto = convertView.findViewById(R.id.tv_nome_produto);
        TextView tvLocalDoacao = convertView.findViewById(R.id.tv_local_doacao);
        TextView tvValidadeProduto = convertView.findViewById(R.id.tv_validade_produto);

        imgProduto.setImageResource(doacao.getImagemProduto());
        tvNomeProduto.setText(doacao.getNomeProduto());
        tvLocalDoacao.setText(doacao.getLocalDoacao());
        tvValidadeProduto.setText("Validade: " + doacao.getValidadeProduto());

        return convertView;
    }
}
