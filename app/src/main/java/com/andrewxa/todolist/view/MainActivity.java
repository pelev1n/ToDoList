package com.andrewxa.todolist.view;

import android.support.v7.app.AppCompatActivity;
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
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.presenter.Presenter;
import com.andrewxa.todolist.utils.myOnClickListener;

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


public class MainActivity extends AppCompatActivity implements Contract.view {

    private RecyclerView recyclerView;
    public TaskAdapter adapter;
    private EditText itemET;
    private Button btn;
    private Presenter presenter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        itemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);

        presenter = new Presenter(this,this);
        adapter = new TaskAdapter(new ArrayList<Task>());
        loadData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) {
                        presenter.addTask(itemET.getText().toString());
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) {
                                Toast.makeText(MainActivity.this,"Task successfully added",Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                compositeDisposable.add(disposable);
                itemET.setText("");
            }
        });

        adapter.setMyOnClickListener(new myOnClickListener() {
            @Override
            public void onConfirmClick(final long id, final String newTask) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) {
                        presenter.editTask(newTask,id);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) {
                                Toast.makeText(MainActivity.this,"Task successfully edited",Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                compositeDisposable.add(disposable);
            }

            @Override
            public void onRemoveClick(final long id) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) {
                        presenter.deleteTask(id);
                        e.onComplete();
                    }
                })      .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) {
                                Toast.makeText(MainActivity.this,"Task successfully deleted",Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(MainActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                compositeDisposable.add(disposable);
                }
        });

    }

    private void loadData() {
        Disposable disposable = presenter.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Exception {
                        adapter.update(tasks);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }


    @Override
    public void updateData(Flowable<List<Task>> tasks) {
        Disposable disposable = presenter.getAllTask()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Exception {
                        adapter.update(tasks);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this,""+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);

    }



    @Override
    public void message(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
