package filedownloader;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class DownloadController {

    @FXML private ProgressBar progressBar1;
    @FXML private ProgressBar progressBar2;
    @FXML private ProgressBar progressBar3;

    @FXML private Label status1;
    @FXML private Label status2;
    @FXML private Label status3;

    @FXML private Button startButton;

    public void initialize() {
    }

    @FXML
    private void startDownloads() {

        resetAll();
        startButton.setDisable(true);
        startButton.setText("Downloading");

        Thread file1Thread = new Thread(new DownloadTask(progressBar1, status1), "File-1-Thread");
        Thread file2Thread = new Thread(new DownloadTask(progressBar2, status2), "File-2-Thread");
        Thread file3Thread = new Thread(new DownloadTask(progressBar3, status3), "File-3-Thread");

        file1Thread.start();
        file2Thread.start();
        file3Thread.start();

        new Thread(() -> {
            try {
                file1Thread.join();
                file2Thread.join();
                file3Thread.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            Platform.runLater(() -> onFinished());
        }).start();
    }

    private void resetAll() {
        ProgressBar[] bars = {
            progressBar1, progressBar2, progressBar3
        };

        Label[] statuses = {
            status1, status2, status3
        };

        for (int i = 0; i < bars.length; i++) {
            bars[i].setProgress(0);
            statuses[i].setText("⏸ Queued");
        }
    }

    private void onFinished() {
        startButton.setDisable(false);
        startButton.setText("Start Again");
        startButton.setStyle(
            "-fx-background-color: #2e7d32;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 8;"
        );
    }
}
