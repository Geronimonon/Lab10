public class ZadanieAsynchroniczne2 extends AsyncTask<Integer, Integer, Void> {

    WeakReference<Button> przyciskStartRef, przyciskCancelRef;
    WeakReference<ProgressBar> pasekPostempuRef;
    int liczbaPowtorzen, czasPauzy, postep;

    public ZadanieAsynchroniczne2(ProgressBar progressBar, Button przyciskStart, Button przyciskCancel) {
        pasekPostempuRef = new WeakReference<>(progressBar);
        przyciskStartRef = new WeakReference<>(przyciskStart);
        przyciskCancelRef = new WeakReference<>(przyciskCancel);
    }

    @Override
    protected Void doInBackground(Integer... integers) {

        liczbaPowtorzen = integers[0];
        czasPauzy = integers[1];

        for(int i = 0; i < liczbaPowtorzen; i++){

            if (isCancelled()) break;

            try {
                Thread.sleep(czasPauzy);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            postep = 100*(i+1) / liczbaPowtorzen;

            publishProgress(postep);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pasekPostempuRef.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        przyciskStartRef.get().setEnabled(true);
        przyciskCancelRef.get().setEnabled(false);
    }

    @Override
    protected void onCancelled() {
        przyciskStartRef.get().setEnabled(true);
        przyciskCancelRef.get().setEnabled(false);
    }
}