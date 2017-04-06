package com.hudati.emlearning.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.hudati.emlearning.R;
import com.hudati.emlearning.activity.LectureActivity;
import com.hudati.emlearning.api.LectureResponse;
import com.hudati.emlearning.model.Lecture;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

/**
 * Created by huylv on 22-Mar-17.
 */

public class GroupLectureAdapter extends AbstractExpandableItemAdapter<GroupLectureAdapter.MyGroupViewHolder, GroupLectureAdapter.MyChildViewHolder> implements View.OnClickListener {
    RecyclerViewExpandableItemManager mExpandableItemManager;
    public ArrayList<LectureResponse.GroupLectures> groupLectures;
    Context context;

    public GroupLectureAdapter(Context c, RecyclerViewExpandableItemManager expMgr) {
        mExpandableItemManager = expMgr;
        context = c;

        setHasStableIds(true); // this is required for expandable feature.

        groupLectures = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            MyGroupItem group = new MyGroupItem(i, "Phase " + (i + 1));
//            for (int j = 0; j < 5; j++) {
//                group.children.add(new MyChildItem(j, "Lesson " + (j + 1)));
//            }
//            groupLectures.add(group);
//        }
    }

    @Override
    public int getGroupCount() {
        return groupLectures.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return groupLectures.get(groupPosition).getLectures().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // This method need to return unique value within all group items.
        return groupLectures.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // This method need to return unique value within the group.
        return Long.parseLong(groupLectures.get(groupPosition).getLectures().get(childPosition).getLectureUnit());
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_item_for_expandable_minimal, parent, false);
        MyGroupViewHolder vh = new MyGroupViewHolder(v);
        vh.itemView.setOnClickListener(this);
        return vh;
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_child_item_for_expandable_minimal, parent, false);
        MyChildViewHolder vh = new MyChildViewHolder(v);
        vh.itemView.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        LectureResponse.GroupLectures group = groupLectures.get(groupPosition);
        holder.textView.setText(group.getHeaderName());
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {
        Lecture child = groupLectures.get(groupPosition).getLectures().get(childPosition);
        holder.textView.setText(child.getLectureTitle());
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        // handles click event manually (to show Snackbar message)
        return false;
    }

    @Override
    public void onClick(View v) {
        RecyclerView rv = RecyclerViewAdapterUtils.getParentRecyclerView(v);
        RecyclerView.ViewHolder vh = rv.findContainingViewHolder(v);

        int rootPosition = vh.getAdapterPosition();
        if (rootPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // need to determine adapter local flat position like this:
        RecyclerView.Adapter rootAdapter = rv.getAdapter();
        int localFlatPosition = WrapperAdapterUtils.unwrapPosition(rootAdapter, this, rootPosition);

        long expandablePosition = mExpandableItemManager.getExpandablePosition(localFlatPosition);
        int groupPosition = RecyclerViewExpandableItemManager.getPackedPositionGroup(expandablePosition);
        int childPosition = RecyclerViewExpandableItemManager.getPackedPositionChild(expandablePosition);

        String message;
        if (childPosition == RecyclerView.NO_POSITION) {
            // Clicked item is a group!

            // toggle expand/collapse
            if (mExpandableItemManager.isGroupExpanded(groupPosition)) {
                mExpandableItemManager.collapseGroup(groupPosition);
                message = "COLLAPSE: Group " + groupPosition;
            } else {
                mExpandableItemManager.expandGroup(groupPosition);
                message = "EXPAND: Group " + groupPosition;
            }
        } else {
            // Clicked item is a child!

            message = "CLICKED: Child " + groupPosition + "-" + childPosition;
            Intent i = new Intent(context, LectureActivity.class);
            Lecture l = groupLectures.get(groupPosition).getLectures().get(childPosition);
            i.putExtra(Utils.INTENT_KEY_ACTION_LECTURE_NAME,l.getLectureTitle());
            i.putExtra(Utils.INTENT_KEY_ACTION_LECTURE_PAGE,l.getActions().getActionSinglePage());
            i.putExtra(Utils.INTENT_ACTION_LECTURE_YOUTUBE,l.getLectureYoutubeCode());
            context.startActivity(i);
        }

    }

//        static abstract class MyBaseItem {
//            public long id;
//            public String text;
//
//            public MyBaseItem(long id, String text) {
//                this.id = id;
//                this.text = text;
//            }
//        }

//    static class MyGroupItem extends MyBaseItem {
//        public final List<MyChildItem> children;
//
//        public MyGroupItem(long id, String text) {
//            super(id, text);
//            children = new ArrayList<>();
//        }
//    }

//    static class MyChildItem extends MyBaseItem {
//        public MyChildItem(long id, String text) {
//            super(id, text);
//        }
//    }

    static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        TextView textView;

        public MyBaseViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    static class MyGroupViewHolder extends MyBaseViewHolder {
        public MyGroupViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View itemView) {
            super(itemView);
        }
    }
}
