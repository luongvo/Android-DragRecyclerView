package cn.cyan.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felipecsl.asymmetricgridview.AGVRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.AsymmetricRecyclerView;
import com.felipecsl.asymmetricgridview.AsymmetricRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.Utils;

import java.util.List;

import cn.cyan.dragrecyclerview.DragRecyclerView;
import cn.cyan.dragrecyclerview.HoldTouchHelper;
import cn.cyan.sample.R;
import cn.cyan.sample.SpacesItemDecoration;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */

public abstract class BaseFragment extends Fragment {

    private List<SampleEntity> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        DragRecyclerView dragRecyclerView = (DragRecyclerView) view.findViewById(R.id.drv);
//        dragRecyclerView.addItemDecoration(new DividerGridItemDecoration());
//        dragRecyclerView.setHasFixedSize(false);
//        dragRecyclerView.setLayoutManager(layoutManager());

        int spacing = Utils.dpToPx(getContext(), 3);
        dragRecyclerView.setRequestedColumnCount(3);
        dragRecyclerView.setDebugging(true);
        dragRecyclerView.setRequestedHorizontalSpacing(spacing);
        dragRecyclerView.addItemDecoration(
                new SpacesItemDecoration(spacing));

        data = initData();
        /** custom setting */

        SampleAdapter sampleAdapter = adapter(data);
        AsymmetricRecyclerViewAdapter asymmetricRecyclerViewAdapter = new AsymmetricRecyclerViewAdapter<RecyclerView.ViewHolder>(
                getContext(), dragRecyclerView, sampleAdapter);
        dragRecyclerView
                .dragEnable(true)
                .showDragAnimation(true)
                .setDragAdapter(asymmetricRecyclerViewAdapter)
                .bindEvent(onItemTouchEvent);
    }


    protected abstract SampleAdapter adapter(List<SampleEntity> data);

    protected abstract RecyclerView.LayoutManager layoutManager();

    HoldTouchHelper.OnItemTouchEvent onItemTouchEvent = new HoldTouchHelper.OnItemTouchEvent() {
        @Override
        public void onLongPress(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            if (((SampleAdapter) ((AsymmetricRecyclerViewAdapter) recyclerView.getAdapter()).getWrappedAdapter()).onItemDrag(position)) {
                ((DragRecyclerView) recyclerView).startDrag(position);
            }
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            String text = data.get(position).getText();
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    };


    protected abstract List<SampleEntity> initData();

}
