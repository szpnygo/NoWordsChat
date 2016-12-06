package info.smemo.nbaseaction.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBaseViewHolder;

public class NBaseBindingAdapter<T> extends RecyclerView.Adapter<NBaseViewHolder> {

    private ArrayList<T> mList;
    private int variable;
    private int layout;

    private OnAdapterClickListener listener;

    private int width = ViewGroup.LayoutParams.MATCH_PARENT;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;

    public NBaseBindingAdapter(ArrayList<T> list, int variable, int layout) {
        this.mList = list;
        this.variable = variable;
        this.layout = layout;
    }

    public NBaseBindingAdapter(ArrayList<T> list, int variable, int layout, int width, int height) {
        this.mList = list;
        this.variable = variable;
        this.layout = layout;
        this.width = width;
        this.height = height;
    }

    @Override
    public NBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        return new NBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NBaseViewHolder holder, int position) {
        final T obj = mList.get(position);
        final int p = position;
        final View view = holder.itemView;
        holder.getBinding().setVariable(variable, mList.get(position));
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(view, p, obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    public synchronized void remove(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public synchronized void add(int position) {
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }


    public interface OnAdapterClickListener<T> {

        void onClick(View view, int position, T object);

    }
}
