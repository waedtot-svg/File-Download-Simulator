# 📥 File Download Simulator

JavaFX application that simulates downloading 3 files at the same time.

Each file runs on its own individual Thread, without using a thread pool.

## Files

- Main.java
- DownloadController.java
- DownloadTask.java
- download.fxml

## Notes

The interface is created using FXML and can be edited with Scene Builder.

Required idea:
- 3 files
- 3 progress bars
- 3 separate threads
- No ExecutorService / no thread pool
