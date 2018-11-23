package com.itskshitizsh.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itskshitizsh.inventoryapp.data.InventoryContract;

import static com.itskshitizsh.inventoryapp.data.InventoryContract.InventoryEntry;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_INVENTORY_LOADER = 0;
    private Uri mCurrentProductUri;

    private EditText mProductNameEditText;
    private EditText mProductPriceEditText;
    private EditText mProductQuantityEditText;
    private Spinner mProductSupplierNameSpinner;
    private EditText mProductSupplierPhoneNumberEditText;

    private int mSupplierName = InventoryContract.InventoryEntry.SUPPLIER_UNKNOWN;

    private boolean mProductHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            Log.d("message", "onTouch");
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Log.d("message", "onCreate");

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        TextView textView = findViewById(R.id.titleText);

        if (mCurrentProductUri == null) {
            setTitle(getString(R.string.add_new_product));
            textView.setText(getString(R.string.add_new_product));
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_product));
            textView.setText(getString(R.string.edit_product));
            getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
        }

        mProductNameEditText = findViewById(R.id.productNameEditText);
        mProductPriceEditText = findViewById(R.id.productPriceEditText);
        mProductQuantityEditText = findViewById(R.id.productQuantityEditText);
        mProductSupplierNameSpinner = findViewById(R.id.productSupplierNameSpinner);
        mProductSupplierPhoneNumberEditText = findViewById(R.id.productSupplierPhoneNumberEditText);

        mProductSupplierNameSpinner.setOnTouchListener(mTouchListener);
        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter productSupplierNameSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_supplier_options, android.R.layout.simple_spinner_item);

        productSupplierNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mProductSupplierNameSpinner.setAdapter(productSupplierNameSpinnerAdapter);

        mProductSupplierNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.supplier_amazon))) {
                        mSupplierName = InventoryEntry.SUPPLIER_AMAZON;
                    } else if (selection.equals(getString(R.string.supplier_flipkart))) {
                        mSupplierName = InventoryEntry.SUPPLIER_FLIPKART;
                    } else if (selection.equals(getString(R.string.supplier_paytm))) {
                        mSupplierName = InventoryEntry.SUPPLIER_PAYTM;
                    } else {
                        mSupplierName = InventoryEntry.SUPPLIER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSupplierName = InventoryEntry.SUPPLIER_UNKNOWN;
            }
        });
    }

    private void saveProduct() {
        String productName;
        String productPrice;
        int productQuantity;
        String productSupplierPhoneNumber;

        if (mCurrentProductUri == null) {

            if (mProductNameEditText.getText().toString().isEmpty()) {
                mProductNameEditText.setError(getString(R.string.error_message));
                mProductNameEditText.requestFocus();
                return;
            } else {
                productName = mProductNameEditText.getText().toString().trim();
            }
            if (mProductPriceEditText.getText().toString().isEmpty()) {
                mProductPriceEditText.setError(getString(R.string.error_message));
                mProductPriceEditText.requestFocus();
                return;
            } else {
                productPrice = mProductPriceEditText.getText().toString().trim();
            }
            if (mProductQuantityEditText.getText().toString().isEmpty()) {
                mProductQuantityEditText.setError(getString(R.string.error_message));
                mProductQuantityEditText.requestFocus();
                return;
            } else {
                productQuantity = Integer.parseInt(mProductQuantityEditText.getText().toString().trim());
            }
            if (mProductSupplierPhoneNumberEditText.getText().toString().isEmpty()) {
                mProductSupplierPhoneNumberEditText.setError(getString(R.string.error_message));
                mProductSupplierPhoneNumberEditText.requestFocus();
                return;
            } else {
                productSupplierPhoneNumber = mProductSupplierPhoneNumberEditText.getText().toString().trim();
            }

            ContentValues values = new ContentValues();
            values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productName);
            values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, productPrice);
            values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, productQuantity);
            values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, mSupplierName);
            values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, productSupplierPhoneNumber);

            try {
                Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
                if (newUri == null) {
                    Toast.makeText(this, getString(R.string.insert_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.insert_successful),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Enter Valid Fields!", Toast.LENGTH_SHORT).show();
            }
        } else {

            if (mProductNameEditText.getText().toString().isEmpty()) {
                mProductNameEditText.setError(getString(R.string.error_message));
                mProductNameEditText.requestFocus();
                return;
            } else {
                productName = mProductNameEditText.getText().toString().trim();
            }
            if (mProductPriceEditText.getText().toString().isEmpty()) {
                mProductPriceEditText.setError(getString(R.string.error_message));
                mProductPriceEditText.requestFocus();
                return;
            } else {
                productPrice = mProductPriceEditText.getText().toString().trim();
            }
            if (mProductQuantityEditText.getText().toString().isEmpty()) {
                mProductQuantityEditText.setError(getString(R.string.error_message));
                mProductQuantityEditText.requestFocus();
                return;
            } else {
                productQuantity = Integer.parseInt(mProductQuantityEditText.getText().toString().trim());
            }
            if (mProductSupplierPhoneNumberEditText.getText().toString().isEmpty()) {
                mProductSupplierPhoneNumberEditText.setError(getString(R.string.error_message));
                mProductSupplierPhoneNumberEditText.requestFocus();
                return;
            } else {
                productSupplierPhoneNumber = mProductSupplierPhoneNumberEditText.getText().toString().trim();
            }

            ContentValues values = new ContentValues();

            values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productName);
            values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, productPrice);
            values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, productQuantity);
            values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, mSupplierName);
            values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, productSupplierPhoneNumber);

            try {
                int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);
                if (rowsAffected == 0) {
                    Toast.makeText(this, getString(R.string.update_failed),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.update_successful),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error while Updating", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        Log.d("message", "open Editor Activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveProduct();
                return true;
            case android.R.id.home:
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRODUCT_PRICE,
                InventoryEntry.COLUMN_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };
        return new CursorLoader(this,
                mCurrentProductUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            String currentName = cursor.getString(nameColumnIndex);
            int currentPrice = cursor.getInt(priceColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);
            int currentSupplierName = cursor.getInt(supplierNameColumnIndex);
            String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

            mProductNameEditText.setText(currentName);
            mProductPriceEditText.setText(String.valueOf(currentPrice));
            mProductQuantityEditText.setText(String.valueOf(currentQuantity));
            mProductSupplierPhoneNumberEditText.setText(String.valueOf(currentSupplierPhone));

            switch (currentSupplierName) {
                case InventoryEntry.SUPPLIER_AMAZON:
                    mProductSupplierNameSpinner.setSelection(1);
                    break;
                case InventoryEntry.SUPPLIER_FLIPKART:
                    mProductSupplierNameSpinner.setSelection(2);
                    break;
                case InventoryEntry.SUPPLIER_PAYTM:
                    mProductSupplierNameSpinner.setSelection(3);
                    break;
                default:
                    mProductSupplierNameSpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mProductNameEditText.setText("");
        mProductPriceEditText.setText("");
        mProductQuantityEditText.setText("");
        mProductSupplierPhoneNumberEditText.setText("");
        mProductSupplierNameSpinner.setSelection(0);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}