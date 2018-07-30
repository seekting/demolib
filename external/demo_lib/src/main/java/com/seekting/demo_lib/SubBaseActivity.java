package com.seekting.demo_lib;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public abstract class SubBaseActivity extends Activity {

    static class SubBaseViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView className;

        public SubBaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class Data {
        Method method;
        String title;
        String desc;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        Method[] methods = this.getClass().getDeclaredMethods();
        final List<Data> datas = new ArrayList<>();
        for (Method method : methods) {
            SubDemo subDemo = method.getAnnotation(SubDemo.class);
            if (subDemo != null) {
                String btnName = subDemo.title();
                if (TextUtils.isEmpty(btnName)) {
                    btnName = method.getName();
                    if (method.getParameterTypes().length > 0) {
                        throw new RuntimeException("method " + method.getName() + " can't has param");
                    }

                }
                method.setAccessible(true);
                Data data = new Data();
                data.title = btnName;
                data.desc = subDemo.desc();
                data.method = method;
                datas.add(data);

            }


        }

        final View.OnClickListener onclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = (Data) v.getTag();
                try {
                    data.method.invoke(SubBaseActivity.this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<SubBaseViewHolder>() {
            @Override
            public SubBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View rootView = layoutInflater.inflate(R.layout.demo_layout, null);
                SubBaseViewHolder viewHolder = new SubBaseViewHolder(rootView);
                viewHolder.title = (TextView) rootView.findViewById(R.id.title);
                viewHolder.description = (TextView) rootView.findViewById(R.id.description);
                viewHolder.className = (TextView) rootView.findViewById(R.id.class_name);
                viewHolder.itemView.setOnClickListener(onclickListener);
                viewHolder.itemView.setBackgroundResource(R.drawable.default_ripple);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(SubBaseViewHolder holder, int position) {
                Data data = datas.get(position);
                holder.title.setText(data.desc);
//                holder.description.setText(data.desc);
                holder.className.setText(data.title);
                holder.itemView.setTag(data);

            }

            @Override
            public int getItemCount() {
                return datas.size();
            }
        };
        recyclerView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
//            GestureDetector mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (childView != null) {
//                        int position = recyclerView.getChildLayoutPosition(childView);
//                        try {
//                            datas.get(position).method.invoke(SubBaseActivity.this);
//                        } catch (IllegalAccessException e1) {
//                            e1.printStackTrace();
//                        } catch (InvocationTargetException e1) {
//                            e1.printStackTrace();
//                        }
//                        return true;
//                    }
//                    return false;
//
//                }
//            });
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                if (mGestureDetector.onTouchEvent(e)) {
//                    return true;
//                }
//                return false;
//            }
//
//
//        });
        final int lineHeight = 1;
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private Paint dividerPaint = new Paint();

            @Override

            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                outRect.top = lineHeight;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

                int childCount = parent.getChildCount();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                dividerPaint.setColor(Color.GRAY);
                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + lineHeight;
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }

            }
        });


    }


}
