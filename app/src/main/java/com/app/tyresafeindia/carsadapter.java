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


public class carsadapter extends  RecyclerView.Adapter<carsadapter.carsviewholder> implements Filterable {

    private Context mCtx;
    private List<cars> carsList;
    private List<cars>carsListFull;
    private OnItemClickListner mListner;

    public carsadapter(List<cars> carsList) {

    }

    public interface OnItemClickListner{
        void OnItemClick(int position);
    }

    public void  OnItemClickListner(OnItemClickListner listner){

        mListner=listner;
    }

    public carsadapter(Context mCtx, List<cars> carsList) {
        this.mCtx = mCtx;
        this.carsList = carsList;
        carsListFull=new ArrayList<>(carsList);
    }

    @NonNull
    @Override
    public carsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.itemobj,null);
        return new carsviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull carsviewholder holder, int position) {

        cars product=carsList.get(position);

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
        return carsList.size();
    }

    class carsviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textvechilename,textprice,textqunty,texttyresize;


        public carsviewholder(@NonNull View itemView) {

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
        return carsfilter;
    }
    private Filter carsfilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<cars>filtredList=new ArrayList<>();

            if (constraint== null||constraint.length()==0){
                filtredList.addAll(carsListFull);

            }else {

                String filtredpattern=constraint.toString().toLowerCase().trim();
                for (cars item:carsListFull){

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
            carsList.clear();
            carsList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
