package com.example.seu_is_18_ict_039;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartProductList;

    public CartAdapter(List<Product> cartProductList) {
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item, parent, false);
        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartProductList.get(position);

        // Bind the product data to the views
        holder.productName.setText(product.getName()); // Display product name
        holder.productPrice.setText("$" + product.getPrice()); // Display product price
        holder.productImage.setImageResource(product.getImageResource()); // Display product image

        // Handle delete button click
        holder.deleteButton.setOnClickListener(v -> {
            cartProductList.remove(position);
            notifyItemRemoved(position);
        });

        // Increment button logic
        holder.incrementButton.setOnClickListener(v -> {
            product.incrementQuantity();
            notifyItemChanged(position); // Update the item view
        });

        // Decrement button logic
        holder.decrementButton.setOnClickListener(v -> {
            product.decrementQuantity();
            notifyItemChanged(position); // Update the item view
        });

        // Update quantity display
        holder.quantityTextView.setText(String.valueOf(product.getQuantity()));
    }


    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice, quantityTextView;
        ImageView productImage;
        Button deleteButton, incrementButton, decrementButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.cartProductPrice);
            productImage = itemView.findViewById(R.id.cartProductImage);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            incrementButton = itemView.findViewById(R.id.incrementButton);
            decrementButton = itemView.findViewById(R.id.decrementButton);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
        }
    }

}
