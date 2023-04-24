public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = System.out::println;
        //Worker worker = new Worker(listener);
        Worker errorWorker = new Worker(listener, errorListener);
        errorWorker.start();
    }

    @FunctionalInterface
    public interface OnTaskErrorListener {
        void onError(String result);
    }

    @FunctionalInterface
    public interface OnTaskDoneListener {
        void onDone(String result);
    }

    public static class Worker {
        private OnTaskDoneListener callback;
        private OnTaskErrorListener errorCallback;

        /*
        public Worker(OnTaskDoneListener callback) {
            this.callback = callback;
        }
        */

        public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
            this.callback = callback;
            this.errorCallback = errorCallback;
        }

        public void start() {
            for (int i = 0; i < 100; i++) {
                if (i == 33) {
                    errorCallback.onError("Task " + i + " ERROR");
                } else {
                    callback.onDone("Task " + i + " is done");
                }
            }
        }
    }
}