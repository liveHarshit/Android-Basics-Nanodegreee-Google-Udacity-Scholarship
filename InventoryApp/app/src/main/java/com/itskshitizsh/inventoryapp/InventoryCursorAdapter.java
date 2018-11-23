package com.itskshitizsh.inventoryapp;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.itskshitizsh.inventoryapp.data.InventoryContract.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        Log.d("Position " + cursor.getPosition() + ":", " bindView() has been called.");

        TextView productNameTextView = view.findViewById(R.id.name_product_text_view);
        TextView productPriceTextView = view.findViewById(R.id.product_price_text_view);
        TextView productQuantityTextView = view.findViewById(R.id.product_quantity_text_view);
        FloatingActionButton productSaleButton = view.findViewById(R.id.sale_button);
        FloatingActionButton productEditButton = view.findViewById(R.id.edit_button);

        final int columnIdIndex = cursor.getColumnIndex(InventoryEntry._ID);
        int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
        int productPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
        int productQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);

        final String productID = cursor.getString(columnIdIndex);
        String productName = cursor.getString(productNameColumnIndex);
        String productPrice = cursor.getString(productPriceColumnIndex);
        final String productQuantity = cursor.getString(productQuantityColumnIndex);

        productSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryActivity Activity = (InventoryActivity) context;
                Activity.productSaleCount(Integer.valueOf(productID), Integer.valueOf(productQuantity));
            }
        });

        productNameTextView.setText(productName);
        productPriceTextView.setText(context.getString(R.string.product_price).concat(" : \n\t\t\t").concat(productPrice).concat(" ").concat("$"));
        productQuantityTextView.setText(context.getString(R.string.product_quantity).concat(" : ").concat(productQuantity));

        productEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), EditorActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, Long.parseLong(productID));
                intent.setData(currentProductUri);
                context.startActivity(intent);
            }
        });
        productNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, Integer.parseInt(productID));
                intent.setData(currentProductUri);
                context.startActivity(intent);
            }
        });
        productPriceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, Integer.parseInt(productID));
                intent.setData(currentProductUri);
                context.startActivity(intent);
            }
        });
        productQuantityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, Integer.parseInt(productID));
                intent.setData(currentProductUri);
                context.startActivity(intent);
            }
        });

    }
}