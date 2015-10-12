package com.sk.cnode.android.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sk.cnode.android.model.api.ApiClient;
import com.sk.cnode.android.model.entity.TabType;
import com.sk.cnode.android.model.entity.Topic;
import com.sk.cnode.android.ui.activity.adapter.TopicAdapter;
import com.sk.cnode.android.ui.widget.listener.RecyclerViewLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerViewLoadMoreListener.OnLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    @Bind(com.sk.cnode.android.R.id.main_refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    @Bind(com.sk.cnode.android.R.id.main_recycler_view)
    RecyclerView recyclerView;


    @Bind(com.sk.cnode.android.R.id.main_layout_no_data)
    protected ViewGroup layoutNoData;


    TabType currentTab = TabType.all;

    int currentPage = 0; // 从未加载
    List<Topic> topicList = new ArrayList<>();


    TopicAdapter topicAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(com.sk.cnode.android.R.layout.fragment_activity_main_content, container, false);

        ButterKnife.bind(this, view);

        initViews();


        return view;
    }

    private void initViews() {

        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(linearLayoutManager);
        topicAdapter = new TopicAdapter(getContext(), topicList);
        recyclerView.setAdapter(topicAdapter);
        recyclerView.addOnScrollListener(new RecyclerViewLoadMoreListener(linearLayoutManager, this, 20));
//        fabNewTopic.attachToRecyclerView(recyclerView);

        refreshLayout.setColorSchemeResources(com.sk.cnode.android.R.color.red_light, com.sk.cnode.android.R.color.green_light, com.sk.cnode.android.R.color.blue_light, com.sk.cnode.android.R.color.orange_light);
        refreshLayout.setOnRefreshListener(this);

//        HandlerUtils.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                refreshLayout.setRefreshing(true);
//                onRefresh();
//            }
//
//        }, 100); // refreshLayout无法直接在onCreate中设置刷新状态


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

        final TabType tab = currentTab;


        Call<String> topicsCall = ApiClient.service.getTopics();
        logger.d("");
        topicsCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response) {


                logger.d(response+"");

                logger.d(response.body() +"");

                if (currentTab == tab && response.body() != null) {
                    topicList.clear();
//                    topicList.addAll(response.body());
                    notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                    currentPage = 1;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (currentTab == tab) {
                    Toast.makeText(getContext(), com.sk.cnode.android.R.string.data_load_faild, Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
            }
        });

    }

    /**
     * 更新列表
     */
    private void notifyDataSetChanged() {
        if (topicList.size() < 20) {
            topicAdapter.setLoading(false);
        }
        topicAdapter.notifyDataSetChanged();
        layoutNoData.setVisibility(topicList.size() == 0 ? View.VISIBLE : View.GONE);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
