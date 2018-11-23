package com.itskshitizsh.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Objects;

import static com.itskshitizsh.inventoryapp.data.InventoryContract.InventoryEntry;

public class InventoryProvider extends ContentProvider {

    private static final int PRODUCTS = 100;
    private static final int PRODUCT_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, PRODUCTS);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", PRODUCT_ID);
    }

    private InventoryDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new InventoryDbHelper((getContext()));
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws IllegalArgumentException {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot Query Unknown URI " + uri);
        }
        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) throws IllegalArgumentException {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return addProduct(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion Is Not Supported For " + uri);
        }
    }

    @Override
    public String getType(@NonNull Uri uri) throws IllegalArgumentException {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI" + uri + " with match " + match);
        }
    }

    private Uri addProduct(Uri uri, ContentValues values) {
        String nameProduct = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
        if (nameProduct == null) {
            throw new IllegalArgumentException("Product Name Requires");
        }

        Integer priceProduct = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_PRICE);
        if (priceProduct != null && priceProduct < 0) {
            throw new IllegalArgumentException("Valid Product Price Requires");
        }

        Integer quantityProduct = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
        if (quantityProduct != null && quantityProduct < 0) {
            throw new IllegalArgumentException("Valid Product Quantity Requires");
        }

        Integer supplierName = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
        if (supplierName == null || !InventoryEntry.isValidSupplierName(supplierName)) {
            throw new IllegalArgumentException("Supplier Name Requires");
        }

        String supplierPhone = values.getAsString(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);
        if (supplierPhone != null && supplierPhone.length() < 10) {
            throw new IllegalArgumentException("Valid Supplier Contact Requires");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.v("message:", "Failed To Insert New Row For " + uri);
            return null;
        } else {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, id);
        }
    }

    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) throws IllegalArgumentException {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Delete Operation Is Not Supported For " + uri);
        }
        if (rowsDeleted != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection, String[]
            selectionArgs) throws IllegalArgumentException {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return updateProduct(uri, contentValues, selection, selectionArgs);
            case PRODUCT_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update Operation Is Not Supported For " + uri);
        }
    }

    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) throws IllegalArgumentException {
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_NAME)) {
            String nameProduct = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
            if (nameProduct == null) {
                throw new IllegalArgumentException("Product Name Requires");
            }
        }
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_PRICE)) {
            Integer priceProduct = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_PRICE);
            if (priceProduct != null && priceProduct < 0) {
                throw new IllegalArgumentException("Valid Product Price Requires");
            }
        }

        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_QUANTITY)) {
            Integer quantityProduct = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            if (quantityProduct != null && quantityProduct < 0) {
                throw new IllegalArgumentException("Valid Product Quantity Requires");
            }
        }
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME)) {
            Integer supplierName = values.getAsInteger(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            if (supplierName == null || !InventoryEntry.isValidSupplierName(supplierName)) {
                throw new IllegalArgumentException("Valid Supplier Name Requires");
            }
        }

        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER)) {
            String supplierPhone = values.getAsString(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);
            if (supplierPhone != null && supplierPhone.length() < 10) {
                throw new IllegalArgumentException("Valid Supplier Contact Requires");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(InventoryEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}