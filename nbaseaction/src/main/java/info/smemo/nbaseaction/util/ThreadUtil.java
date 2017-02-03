package info.smemo.nbaseaction.util;

import java.util.List;

import info.smemo.nbaseaction.app.AppConstant;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThreadUtil {

    public static <T> void newThreadWithMainObj(final ThreadRunnable<T> runnable) {
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                subscriber.onNext(runnable.inThread());
                subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<T>() {

                    @Override
                    public void accept(T t) throws Exception {
                        runnable.inMain(t);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogHelper.e(AppConstant.TAG, "ThreadUtil:" + throwable.getMessage());
                        throw Exceptions.propagate(throwable);
                    }
                })
                .subscribe();
    }

    public static <T> void newThreadWithMainList(final ThreadRunnableList<T> runnable) {
        Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> subscriber) throws Exception {
                subscriber.onNext(runnable.inThread());
                subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<T>>() {
                    @Override
                    public void accept(List<T> ts) throws Exception {
                        runnable.inMain(ts);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogHelper.e(AppConstant.TAG, "ThreadUtil:" + throwable.getMessage());
                        throw Exceptions.propagate(throwable);
                    }
                })
                .subscribe();
    }

    public static void newThreadWithMain(final ThreadRunnableVoid runnable) {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> subscriber) throws Exception {
                runnable.inThread();
                subscriber.onNext("next");
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        runnable.inMain();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogHelper.e(AppConstant.TAG, "ThreadUtil:" + throwable.getMessage());
                        throw Exceptions.propagate(throwable);
                    }
                })
                .subscribe();

    }

    public static void newThreadMain(final ThreadRunnableMain runnableMain) {
        Observable.just("newx").subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        runnableMain.inMain();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogHelper.e(AppConstant.TAG, "ThreadUtil:" + throwable.getMessage());
                        throw Exceptions.propagate(throwable);
                    }
                })
                .subscribe();
    }

    public interface ThreadRunnableMain {

        void inMain();

    }


    public interface ThreadRunnableVoid {

        void inThread();

        void inMain();
    }

    public interface ThreadRunnable<T> {

        T inThread();

        void inMain(T t);

    }

    public interface ThreadRunnableList<T> {

        List<T> inThread();

        void inMain(List<T> t);

    }

}
