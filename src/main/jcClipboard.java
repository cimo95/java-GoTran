import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class jcClipboard extends Thread implements ClipboardOwner{

    interface EntryListener {
        void onCopy(String data);
    }

    private final Clipboard cbKlip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private EntryListener elWadah;

    public void setWadah(EntryListener elWadah) {
        this.elWadah = elWadah;
    }

    public void prosesIsi(Transferable tfIsi) {
        try {
            String sIsi = (String) (tfIsi.getTransferData(DataFlavor.stringFlavor));

            if (elWadah != null) {
                elWadah.onCopy(sIsi);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void lostOwnership(Clipboard cbOwner, Transferable tfOwner) {
        try {
            sleep(200);
        } catch (Exception ignored) {
        }

        Transferable tfIsi = cbOwner.getContents(this);
        prosesIsi(tfIsi);
        regainOwnership(cbOwner, tfIsi);
    }

    public void regainOwnership(Clipboard cbOwner, Transferable tfOwner) {
        cbOwner.setContents(tfOwner, this);
    }

    public void run() {
        Transferable tfOut = cbKlip.getContents(this);
        regainOwnership(cbKlip, tfOut);

        while(true);
    }

}