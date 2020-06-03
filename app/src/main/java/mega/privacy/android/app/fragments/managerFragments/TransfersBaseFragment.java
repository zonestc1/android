package mega.privacy.android.app.fragments.managerFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mega.privacy.android.app.R;
import mega.privacy.android.app.components.SimpleDividerItemDecoration;
import mega.privacy.android.app.fragments.BaseFragment;
import mega.privacy.android.app.lollipop.ManagerActivityLollipop;

import static mega.privacy.android.app.components.transferWidget.TransfersManagement.*;

public class TransfersBaseFragment extends BaseFragment {

    protected ImageView emptyImage;
    protected TextView emptyText;
    private RelativeLayout getMoreQuotaView;
    protected RecyclerView listView;
    protected LinearLayoutManager mLayoutManager;

    protected ManagerActivityLollipop managerActivity;

    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.fragment_transfers, container, false);

        listView = v.findViewById(R.id.transfers_list_view);
        listView.addItemDecoration(new SimpleDividerItemDecoration(context, outMetrics));
        mLayoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(mLayoutManager);
        listView.setHasFixedSize(true);
        listView.setItemAnimator(null);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                checkScroll();
            }
        });

        emptyImage = v.findViewById(R.id.transfers_empty_image);
        emptyText = v.findViewById(R.id.transfers_empty_text);

        getMoreQuotaView = v.findViewById(R.id.get_more_quota_view);
        v.findViewById(R.id.get_more_quota_upgrade_button).setOnClickListener(v1 -> ((ManagerActivityLollipop) context).navigateToUpgradeAccount());

        setGetMoreQuotaViewVisibility();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        managerActivity = (ManagerActivityLollipop) context;
    }

    public void checkScroll() {
        if (listView == null) return;

        ((ManagerActivityLollipop) context).changeActionBarElevation(listView.canScrollVertically(-1));
    }

    protected void setEmptyView(int size) {
        if (size == 0) {
            emptyImage.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyImage.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    public void setGetMoreQuotaViewVisibility() {
        getMoreQuotaView.setVisibility(isOnTransferOverQuota() ? View.VISIBLE : View.GONE);
    }
}
