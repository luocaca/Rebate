package Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.integralmanagement.R;

import java.util.List;

public abstract class DeviceManagementAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private static final String TAG = DeviceManagementAdapter.class.getSimpleName();



    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DeviceManagementAdapter(List data) {
        super(data);
        addItemType(0, R.layout.item_equipment);
        addItemType(1, R.layout.item_account);
//        addItemType(TYPE_PERSON, R.layout.item_service);
    }

//
//
//    public boolean isOnlyExpandOne() {
//        return isOnlyExpandOne;
//    }
//
//    public void setOnlyExpandOne(boolean onlyExpandOne) {
//        isOnlyExpandOne = onlyExpandOne;
//    }

}
