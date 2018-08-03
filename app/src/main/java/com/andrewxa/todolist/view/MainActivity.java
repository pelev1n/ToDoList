package com.andrewxa.todolist.view;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrewxa.todolist.R;
import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.adapter.TaskAdapter;
import com.andrewxa.todolist.contract.ToDoView;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.presenter.Presenter;
import com.andrewxa.todolist.utils.myOnClickListener;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends MvpAppCompatActivity implements ToDoView {

    @InjectPresenter
    Presenter presenter;

    private RecyclerView recyclerView;
    public TaskAdapter adapter;
    private EditText itemET;
    private Button btn;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);

        presenter = new Presenter(this, this);
        adapter = new TaskAdapter(new ArrayList<Task>());

        loadData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                compositeDisposable.add(Observable.create(subscribe -> {
                    presenter.addTask(itemET.getText().toString());
                    subscribe.onComplete();
                        }
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(accept -> {
                            Toast.makeText(MainActivity.this, "Task successfully added", Toast.LENGTH_SHORT).show();
                        }, throwable ->
                        {
                            Toast.makeText(MainActivity.this, "Task successfully added", Toast.LENGTH_SHORT).show();
                        }));
                itemET.setText("");
            }
        });

        adapter.setMyOnClickListener(new myOnClickListener() {
            @Override
            public void onConfirmClick(final long id, final String newTask) {

                compositeDisposable.add(Observable.create(subscribe -> {
                    presenter.editTask(newTask, id);
                    subscribe.onComplete();
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(accept -> {

                            Toast.makeText(MainActivity.this, "Task successfully edited", Toast.LENGTH_SHORT).show();
                        }, throwable -> {
                            Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));
            }

            @Override
            public void onRemoveClick(final long id) {
                compositeDisposable.add(Observable.create(subscribe -> {
                    presenter.deleteTask(id);
                    subscribe.onComplete();
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(accept -> {
                            Toast.makeText(MainActivity.this, "Task successfully deleted", Toast.LENGTH_SHORT).show();
                        }, throwable -> {
                            Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }));
            }
        });
    }

    private void loadData() {
        compositeDisposable.add(presenter.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(tasks -> {
                    adapter.update(tasks);
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    @Override
    public void updateData(Flowable<List<Task>> tasks) {
        compositeDisposable.add(presenter.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(taskz -> {
                    adapter.update(taskz);
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
