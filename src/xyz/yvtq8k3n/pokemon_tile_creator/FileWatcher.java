package xyz.yvtq8k3n.pokemon_tile_creator;

import java.io.File;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class FileWatcher extends Thread {
    private static int DEFAULT_DELAY = 1000;
    private final File file;
    private int delay;
    private AtomicBoolean stop = new AtomicBoolean(false);

    public FileWatcher(File file) {
        this.file = file;
        this.delay = DEFAULT_DELAY;
        setDaemon(false);
    }

    public boolean isStopped() { return stop.get(); }
    public void stopThread() { stop.set(true); }

    protected abstract void doOnChange();

    @Override
    public void run() {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = file.toPath().getParent();
            path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
            while (!isStopped()) {
                WatchKey key = watcher.take();

                // Prevent receiving two separate ENTRY_MODIFY events: file modified
                // and timestamp updated. Instead, receive one ENTRY_MODIFY event
                // with two counts.
                Thread.sleep( delay );
                
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        Thread.yield();
                        continue;
                    } else if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY
                            && filename.toString().equals(file.getName())) {
                        System.out.println(file.getName() + ": " + kind.name());
                        doOnChange();
                    }
                    boolean valid = key.reset();
                    if (!valid) { break; }
                }
                Thread.yield();
            }
        } catch (Throwable e) {
            // Log or rethrow the error
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
