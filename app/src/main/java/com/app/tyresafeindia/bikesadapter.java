package com.app.tyresafeindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class bikesadapter extends  RecyclerView.Adapter<bikesadapter.bikesviewholder> implements Filterable {

    private Context mCtx;
    private List<bikes> bikesList;
    private List<bikes>bikesListFull;
    private OnItemClickListner mListner;

    public bikesadapter(List<bikes> bikesList) {

    }

    public interface OnItemClickListner{
        void OnItemClick(int position);
    }

    public void  OnItemClickListner(OnItemClickListner listner){

        mListner=listner;
    }

    public bikesadapter(Context mCtx, List<bikes> bikesList) {
        this.mCtx = mCtx;
        this.bikesList = bikesList;
        bikesListFull=new ArrayList<>(bikesList);
    }

    @NonNull
    @Override
    public bikesviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.itemobj,null);
        return new bikesviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull bikesviewholder holder, int position) {

        bikes product=bikesList.get(position);

        holder.textvechilename.setText(product.getVechilename());

        holder.texttyresize.setText("Size: "+product.getTyresize());
        holder.textprice.setText(String.valueOf("Price: "+product.getPrice()));

        Glide.with(mCtx)
                .load(product.getImageurl())
                .into(holder.imageView);
        // holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }

    @Override
    public int getItemCount() {
        return bikesList.size();
    }

    class bikesviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textvechilename,textprice,textqunty,texttyresize;


        public bikesviewholder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textvechilename=itemView.findViewById(R.id.vechilename);

            texttyresize=itemView.findViewById(R.id.texttyresize);
            //textqunty=itemView.findViewById(R.id.textViewPrice);
            textprice=itemView.findViewById(R.id.textprice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListner!=null){

                        int position= getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListner.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public Filter getFilter() {
        return bikesfilter;
    }
    private Filter bikesfilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<bikes>filtredList=new ArrayList<>();

            if (constraint== null||constraint.length()==0){
                filtredList.addAll(bikesListFull);

            }else {

                String filtredpattern=constraint.toString().toLowerCase().trim();
                for (bikes item:bikesListFull){

                    if (item.getVechilename().toLowerCase().contains( filtredpattern)){

                        filtredList.add(item);

                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filtredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraints, FilterResults results ) {
            bikesList.clear();
            bikesList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
