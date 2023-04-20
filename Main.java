public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = System.out::println;
        Worker worker = new Worker(listener);
        Worker errorWorker = new Worker(errorListener);
        worker.start();
        errorWorker.error();
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

        public Worker(OnTaskDoneListener callback) {
            this.callback = callback;
        }

        public Worker(OnTaskErrorListener errorCallback) {
            this.errorCallback = errorCallback;
        }

        public void start() {
            for (int i = 0; i < 33; i++) {
                callback.onDone("Task " + i + " is done");
            }
            for (int i = 34; i < 100; i++) {
                callback.onDone("Task " + i + " is done");
            }
        }

        public void error() {
            for (int i = 33; i < 34; i++) {
                errorCallback.onError("Task " + i + " error");
            }
        }
    }
}