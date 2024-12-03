package com.example.seu_is_18_ict_039;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> productListFull; // For search functionality
    private OnAddToCartListener addToCartListener;

    public interface OnAddToCartListener {
        void onAddToCart(Product product);
    }

    public ProductAdapter(List<Product> productList, OnAddToCartListener listener) {
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList); // Copy for filtering
        this.addToCartListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product_adapter, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText("$" + product.getPrice());
        holder.productImage.setImageResource(product.getImageResource());
        holder.addToCart.setOnClickListener(v -> addToCartListener.onAddToCart(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filter(String text) {
        productList.clear();
        if (text.isEmpty()) {
            productList.addAll(productListFull);
        } else {
            for (Product product : productListFull) {
                if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productDescription, productPrice;
        ImageView productImage, addToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            addToCart = itemView.findViewById(R.id.addToCart);
        }
    }
}
