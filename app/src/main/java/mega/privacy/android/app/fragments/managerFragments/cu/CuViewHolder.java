package mega.privacy.android.app.fragments.managerFragments.cu;

import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import mega.privacy.android.app.R;

abstract class CuViewHolder extends RecyclerView.ViewHolder {

    public CuViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(int position, CuNode node, CameraUploadsAdapter.Listener listener) {
        if (handleClick() && node.getNode() != null) {
            itemView.setOnClickListener(v -> listener.onNodeClicked(position, node));
            itemView.setOnLongClickListener(v -> {
                listener.onNodeLongClicked(position, node);
                return true;
            });
        }

        bind(node);
    }

    protected abstract void bind(CuNode node);

    protected boolean handleClick() {
        return true;
    }

    static void setViewSize(View grid, View icSelected, SimpleDraweeView imageView,
            CuItemSizeConfig itemSizeConfig) {
        GridLayoutManager.LayoutParams params =
                (GridLayoutManager.LayoutParams) grid.getLayoutParams();
        params.width = itemSizeConfig.getGridSize();
        params.height = itemSizeConfig.getGridSize();
        grid.setLayoutParams(params);

        int imageViewPadding = itemSizeConfig.getSelectedPadding();
        imageView.setPadding(imageViewPadding, imageViewPadding, imageViewPadding,
                imageViewPadding);

        FrameLayout.LayoutParams icSelectedParams =
                (FrameLayout.LayoutParams) icSelected.getLayoutParams();
        icSelectedParams.width = itemSizeConfig.getIcSelectedSize();
        icSelectedParams.height = itemSizeConfig.getIcSelectedSize();
        icSelectedParams.topMargin = itemSizeConfig.getIcSelectedMargin();
        icSelectedParams.setMarginStart(itemSizeConfig.getIcSelectedMargin());
        icSelected.setLayoutParams(icSelectedParams);
    }

    static void updateThumbnailDisplay(SimpleDraweeView thumbnail, CuNode node,
            CuItemSizeConfig itemSizeConfig) {
        if (node.getThumbnail() != null) {
            thumbnail.setImageURI(Uri.fromFile(node.getThumbnail()));
        } else {
            thumbnail.setImageResource(R.drawable.ic_image_thumbnail);
        }

        thumbnail.getHierarchy()
                .setRoundingParams(RoundingParams.fromCornersRadius(
                        node.isSelected() ? itemSizeConfig.getRoundCornerRadius() : 0));
    }
}
