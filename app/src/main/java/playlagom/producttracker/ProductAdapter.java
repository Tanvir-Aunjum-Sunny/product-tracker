package playlagom.producttracker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Product> productList = Collections.emptyList();

    // CREATE constructor to initialize context and productList sent from ProductListActivity
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.productList = productList;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_list_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind productList
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        // Get product position of item in RecyclerView to bind productList and assign values from list
        final MyHolder myHolder = (MyHolder) holder;
        final Product product = productList.get(position);
        final String loc = product.tvLocation;

        /**
         * dynamic init
         **/

        // column 1
        // column 2
        myHolder.tvShopName.setText(product.tvShopName);
        myHolder.tvRatingValue.setText(product.tvRatingValue);
        myHolder.tvNumOfReview.setText(product.tvNumOfReview);

        // column 3
        if (!product.order) {
            myHolder.ivTickIcon.setVisibility(View.VISIBLE);
            myHolder.tvQuantity.setVisibility(View.GONE);
            myHolder.llEditDelete.setVisibility(View.GONE);
        } else {
            myHolder.ivTickIcon.setVisibility(View.GONE);
            myHolder.tvQuantity.setVisibility(View.VISIBLE);
            myHolder.llEditDelete.setVisibility(View.VISIBLE);
        }
        // column 4
//        myHolder.tvLocation.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        myHolder.tvLocation.setText(product.tvLocation);
        myHolder.tvPrice.setText(product.tvPrice);
        myHolder.tvServiceTime.setText(product.tvServiceTime);

        // handle onclick listener
        myHolder.ivTickIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Order added! " + loc, Toast.LENGTH_SHORT).show();

                //myHolder.ivTickIcon.setVisibility(View.GONE);
                //myHolder.tvQuantity.setVisibility(View.VISIBLE);
                //myHolder.llEditDelete.setVisibility(View.VISIBLE);

                productList.get(position).order = true;

                // init xml to java
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.popup, null);

                // init popup window
                Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.popup);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.show();

//                context.startActivity(new Intent(context, PopUp.class));
            }
        });
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // column 1
        // column 2
        public TextView tvShopName, tvRatingValue, tvNumOfReview;

        // column 3
        LinearLayout llTickQuantity, llEditDelete;
        public ImageView ivTickIcon, ivEditIcon, ivDeleteIcon;
        public TextView tvQuantity;

        // column 4
        public TextView tvLocation, tvPrice, tvServiceTime;

        // create constructor to get widget reference
        public MyHolder(final View itemView) {
            super(itemView);

            // init xml with java
            // column 1 (Shop image)

            // column 2 (shop review)
            tvShopName = itemView.findViewById(R.id.tvShopName);
            tvRatingValue = itemView.findViewById(R.id.tvRatingValue);
            tvNumOfReview = itemView.findViewById(R.id.tvNumOfReview);

            // column 3 (order)
            llTickQuantity = itemView.findViewById(R.id.llTickQuantity);
            llEditDelete = itemView.findViewById(R.id.llEditDelete);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            ivTickIcon = itemView.findViewById(R.id.ivTickIcon);
            ivEditIcon = itemView.findViewById(R.id.ivEditIcon);
            ivDeleteIcon = itemView.findViewById(R.id.ivDeleteIcon);

            // column 4 (loc, cost, time)
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvServiceTime = itemView.findViewById(R.id.tvServiceTime);

//            ivTickIcon.setOnClickListener(ivTickIconListener);

            itemView.setOnClickListener(this);

//            ivTickIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(itemView.getContext(), "Order added!  " + tvLocation, Toast.LENGTH_SHORT).show();
//                }
//            });
        }

//        View.OnClickListener ivTickIconListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(itemView.getContext(), "Order added!  ", Toast.LENGTH_SHORT).show();
//            }
//        };



        // Click event for all items
        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Product product = productList.get(position);

            Toast.makeText(context, "Item clicked! \n" + "item: " + product.tvLocation, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(v.getContext(), ShopViewActivity.class);

//            ComplexproductList complexproductList = new ComplexproductList();
//            complexproductList.questionTitle = product.fishName;
//            complexproductList.answer1 = product.answer1;
//            i.putExtra("obj1", complexproductList);

//            String yourDescription = "Student";
//            i.putExtra("description", yourDescription);
            v.getContext().startActivity(i);
        }
    }
}