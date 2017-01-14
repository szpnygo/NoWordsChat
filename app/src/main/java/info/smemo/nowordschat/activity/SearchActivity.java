package info.smemo.nowordschat.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.android.databinding.library.baseAdapters.BR;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.SearchContract;
import info.smemo.nowordschat.databinding.ActivitySearchBinding;
import info.smemo.nowordschat.presenter.SearchPresenter;

public class SearchActivity extends BaseCompatActivity implements SearchContract.View {

    private ActivitySearchBinding binding;

    private SearchPresenter presenter;

    private NBaseBindingAdapter searchAdapter;

    private SearchView mSearchView;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("搜索");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        presenter = new SearchPresenter(this);

        initView();
    }

    private void initView() {
        searchAdapter = new NBaseBindingAdapter<>(presenter.getData(), BR.bean, R.layout.item_search_friend);
        binding.searchList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.searchList.setAdapter(searchAdapter);
    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        menu.findItem(R.id.action_search).expandActionView();
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("搜索好友");
        mSearchView.setQuery(getIntent().getStringExtra("query"), true);
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setIconified(false);
        mSearchView.clearFocus();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        presenter.search(getQuery());
        return true;
    }


    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public void notifyDataSetChanged() {
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void startLoading() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        binding.progress.setVisibility(View.GONE);
    }

    @Override
    public String getQuery() {
        if (mSearchView != null)
            return mSearchView.getQuery().toString();
        return "";
    }
}
