import org.drjekyll.fontchooser.FontDialog;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Arachmadi Putra @ Cimosoft Codelicious, Org
 */
public class jfUtama extends JFrame implements jcClipboard.EntryListener {

    public String[] sBhsNama = {"Afrikaans", "Albanian", "Amharic",
            "Arabic", "Armenian", "Azerbaijani", "Basque", "Belarusian", "Bengali",
            "Bosnian", "Bulgarian", "Catalan", "Cebuano", "Chichewa",
            "Chinese {Simplified}", "Chinese {Traditional}", "Corsican", "Croatian",
            "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian", "Filipino",
            "Finnish", "French", "Frisian", "Galician", "Georgian", "German", "Greek",
            "Gujarati", "Haitian Creole", "Hausa", "Hawaiian", "Hebrew {iw}",
            "Hebrew {he}", "Hindi", "Hmong", "Hungarian", "Icelandic", "Igbo",
            "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada",
            "Kazakh", "Khmer", "Korean", "Kurdish", "Kyrgyz", "Lao", "Latin", "Latvian",
            "Lithuanian", "Luxembourgish", "Macedonian", "Malagasy", "Malay",
            "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian",
            "Myanmar", "Nepali", "Norwegian", "Odia", "Pashto", "Persian",
            "Polish", "Portuguese", "Punjabi", "Romanian", "Russian", "Samoan",
            "Scots Gaelic", "Serbian", "Sesotho", "Shona", "Sindhi", "Sinhala",
            "Slovak", "Slovenian", "Somali", "Spanish", "Sundanese", "Swahili",
            "Swedish", "Tajik", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian",
            "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Welsh", "Xhosa", "Yiddish",
            "Yoruba", "Zulu"};
    public String[] sBhsId = {"af", "sq", "am", "ar", "hy", "az",
            "eu", "be", "bn", "bs", "bg", "ca", "ceb", "ny", "zh-cn", "zh-tw", "co",
            "hr", "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr", "fy", "gl",
            "ka", "de", "el", "gu", "ht", "ha", "haw", "iw", "he", "hi", "hmn", "hu",
            "is", "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "ku",
            "ky", "lo", "la", "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi",
            "mr", "mn", "my", "ne", "no", "or", "ps", "fa", "pl", "pt", "pa", "ro",
            "ru", "sm", "gd", "sr", "st", "sn", "sd", "si", "sk", "sl", "so", "es",
            "su", "sw", "sv", "tg", "ta", "te", "th", "tr", "uk", "ur", "ug", "uz",
            "vi", "cy", "xh", "yi", "yo", "zu"};

    public String sAK = "", sCurrentPath, sCurrentName = "", sVer = "1.0";
    public int iLastTextSumber, iLastTextHasil, iLastSubSumber, iLastSubHasil;
    public boolean bGetClipboard, bSetClipboard, bOnTransClipboard, bKTT = false;

    public jfUtama() {
        initComponents();
        initNamaConfig();
        initEvents();
    }

    public static void main(String[] args) {
        jfUtama jfu = new jfUtama();
        jfu.setVisible(true);
    }

    private JComboBox cmbTextSumber;
	private JComboBox cmbTextHasil;
    private JTextPane taTextSumber;
    private JTextPane taTextHasil;
	private JButton bTextTerjemah;
	private JButton bTextOpsi;
	private JButton bTextGanti;
	private JPopupMenu pmTextOpsi;
    private JCheckBoxMenuItem miAOT;
	private JMenuItem miReset;
	private JMenuItem miActivate;
	private JMenuItem miAbout;
	private JCheckBoxMenuItem miACTC;
	private JCheckBoxMenuItem miACFC;
	private JCheckBoxMenuItem miKTT;
    private JMenuItem miSF;
	private JMenuItem miSB;
	private JMenuItem miRF;
	private JMenuItem miRB;
	private JMenuItem miCA;
	private JPopupMenu pmTextCM;
	private JMenuItem miPaste;
	private JMenuItem miSelectall;
	private JMenuItem miCopy;
	private JMenuItem miCut;
	private JMenuItem miClear;

    private void initComponents() {
        JPanel panel1 = new JPanel();
		cmbTextSumber = new JComboBox();
		cmbTextHasil = new JComboBox();
        JScrollPane scrollPane1 = new JScrollPane();
		taTextSumber = new JTextPane();
        JScrollPane scrollPane2 = new JScrollPane();
		taTextHasil = new JTextPane();
		bTextTerjemah = new JButton();
		bTextOpsi = new JButton();
		bTextGanti = new JButton();
		pmTextOpsi = new JPopupMenu();
        JMenu menu1 = new JMenu();
		miAOT = new JCheckBoxMenuItem();
		miReset = new JMenuItem();
		miActivate = new JMenuItem();
		miAbout = new JMenuItem();
		miACTC = new JCheckBoxMenuItem();
		miACFC = new JCheckBoxMenuItem();
		miKTT = new JCheckBoxMenuItem();
        JMenu mFNB = new JMenu();
		miSF = new JMenuItem();
		miSB = new JMenuItem();
		miRF = new JMenuItem();
		miRB = new JMenuItem();
		miCA = new JMenuItem();
		pmTextCM = new JPopupMenu();
		miPaste = new JMenuItem();
		miSelectall = new JMenuItem();
		miCopy = new JMenuItem();
		miCut = new JMenuItem();
		miClear = new JMenuItem();

		//======== this ========
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("JGoTranLite 1.0");
		setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("ikon.png"))).getImage());
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== panel1 ========
		{
			panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
			swing. border. EmptyBorder( 0, 0, 0, 0) , "\u0043\u0069\u006d\u006f\u0073\u006f\u0066\u0074\u0020\u0043\u006f\u0064\u0065\u006c\u0069\u0063\u0069\u006f\u0075\u0073\u002c\u0020\u004f\u0072\u0067", javax. swing. border
			. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialog"
			, Font .PLAIN ,12 ), java. awt. Color. black) , panel1. getBorder
			( )) ); panel1. addPropertyChangeListener (e -> {if ("border" .equals (e .getPropertyName () )) throw new RuntimeException
			( ); });
			panel1.setLayout(null);

			//---- cmbTextSumber ----
			cmbTextSumber.setBackground(Color.white);
			cmbTextSumber.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			cmbTextSumber.setToolTipText("Source Language");
			panel1.add(cmbTextSumber);
			cmbTextSumber.setBounds(10, 10, 125, 25);

			//---- cmbTextHasil ----
			cmbTextHasil.setBackground(Color.white);
			cmbTextHasil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			cmbTextHasil.setToolTipText("Destination Language");
			panel1.add(cmbTextHasil);
			cmbTextHasil.setBounds(200, 10, 128, 25);

			//======== scrollPane1 ========
			{
				scrollPane1.setFont(new Font("Segoe UI", Font.PLAIN, 12));

				//---- taTextSumber ----
				taTextSumber.setBackground(Color.black);
				taTextSumber.setFont(new Font("Consolas", Font.PLAIN, 14));
				taTextSumber.setSelectedTextColor(Color.white);
				taTextSumber.setSelectionColor(Color.black);
				taTextSumber.setForeground(Color.white);
				taTextSumber.setToolTipText("Source Text");
				taTextSumber.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				scrollPane1.setViewportView(taTextSumber);
			}
			panel1.add(scrollPane1);
			scrollPane1.setBounds(10, 40, 318, 100);

			//======== scrollPane2 ========
			{
				scrollPane2.setFont(new Font("Segoe UI", Font.PLAIN, 12));

				//---- taTextHasil ----
				taTextHasil.setBackground(new Color(0x000080));
				taTextHasil.setFont(new Font("Consolas", Font.PLAIN, 14));
				taTextHasil.setEditable(false);
				taTextHasil.setSelectedTextColor(Color.white);
				taTextHasil.setSelectionColor(Color.black);
				taTextHasil.setForeground(Color.green);
				taTextHasil.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				taTextHasil.setToolTipText("Result Text");
				taTextHasil.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				scrollPane2.setViewportView(taTextHasil);
			}
			panel1.add(scrollPane2);
			scrollPane2.setBounds(10, 175, 318, 100);

			//---- bTextTerjemah ----
			bTextTerjemah.setText("TRANSLATE");
			bTextTerjemah.setBackground(Color.white);
			bTextTerjemah.setFont(new Font("Segoe UI", Font.BOLD, 12));
			bTextTerjemah.setToolTipText("Begin translate your text above.");
			bTextTerjemah.setDoubleBuffered(true);
			panel1.add(bTextTerjemah);
			bTextTerjemah.setBounds(10, 145, 150, 24);

			//---- bTextOpsi ----
			bTextOpsi.setText("OPTIONS");
			bTextOpsi.setBackground(Color.white);
			bTextOpsi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			panel1.add(bTextOpsi);
			bTextOpsi.setBounds(240, 145, 87, 24);

			//---- bTextGanti ----
			bTextGanti.setText("<->");
			bTextGanti.setBackground(Color.white);
			bTextGanti.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			bTextGanti.setToolTipText("Swap Language");
			panel1.add(bTextGanti);
			bTextGanti.setBounds(138, 10, 59, 25);
		}
		contentPane.add(panel1);
		panel1.setBounds(0, 0, 335, 290);

		{
			// compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(null);

		//======== pmTextOpsi ========
		{

			//======== menu1 ========
			{
				menu1.setText("MENU");

				//---- miAOT ----
				miAOT.setText("Always on Top");
				miAOT.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				menu1.add(miAOT);

				//---- miReset ----
				miReset.setText("Reset");
				miReset.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				menu1.add(miReset);

				//---- miActivate ----
				miActivate.setText("Access Key");
				miActivate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				menu1.add(miActivate);

				//---- miAbout ----
				miAbout.setText("About");
				miAbout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				menu1.add(miAbout);
			}
			pmTextOpsi.add(menu1);
			pmTextOpsi.addSeparator();

			//---- miACTC ----
			miACTC.setText("Auto Copy to Clipboard");
			miACTC.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			pmTextOpsi.add(miACTC);

			//---- miACFC ----
			miACFC.setText("Auto Copy from Clipboard");
			miACFC.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			pmTextOpsi.add(miACFC);

			//---- miKTT ----
			miKTT.setText("Keep Translation Text");
			miKTT.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			pmTextOpsi.add(miKTT);
			pmTextOpsi.addSeparator();

			//======== mFNB ========
			{
				mFNB.setText("Font & Background");
				mFNB.setFont(new Font("Segoe UI", Font.PLAIN, 12));

				//---- miSF ----
				miSF.setText("Source Font");
				miSF.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				mFNB.add(miSF);

				//---- miSB ----
				miSB.setText("Source Background");
				miSB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				mFNB.add(miSB);

				//---- miRF ----
				miRF.setText("Result Font");
				miRF.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				mFNB.add(miRF);

				//---- miRB ----
				miRB.setText("Result Background");
				miRB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				mFNB.add(miRB);
			}
			pmTextOpsi.add(mFNB);
			pmTextOpsi.addSeparator();

			//---- miCA ----
			miCA.setText("Clear All");
			miCA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			pmTextOpsi.add(miCA);
		}

		//======== pmTextCM ========
		{

			//---- miPaste ----
			miPaste.setText("Paste");
			miPaste.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
			pmTextCM.add(miPaste);

			//---- miSelectall ----
			miSelectall.setText("Select All");
			miSelectall.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
			pmTextCM.add(miSelectall);

			//---- miCopy ----
			miCopy.setText("Copy");
			miCopy.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
			pmTextCM.add(miCopy);

			//---- miCut ----
			miCut.setText("Cut");
			miCut.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
			pmTextCM.add(miCut);

			//---- miClear ----
			miClear.setText("Clear");
			miClear.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
			pmTextCM.add(miClear);
		}
    }

    private void initNamaConfig() {
        String path = jfUtama.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            int iSubstr = path.contains(":") ? 1 : 0;
            sCurrentPath = URLDecoder.decode(path.substring(iSubstr, path.lastIndexOf("/") + 1), StandardCharsets.UTF_8);
            sCurrentName = URLDecoder.decode(path.substring(iSubstr, path.length()-4), StandardCharsets.UTF_8);
        } catch (Exception ignored) {}
    }

    String onTrans(String sTeksSumber, String sIdSumber, String sIdHasil) {
        StringBuilder sHasil = new StringBuilder();
        try {
            bTextTerjemah.setText("TRANSLATING...");
            bTextTerjemah.setEnabled(false);
            /*
            * Dengan rincian ini, kamu juga bisa membuat aplikasi serupa
            * With this detail, you also be able to create the same application
            * */
            URL urlServer = new URL("https://gotran.cimosoft.com/j/translate");
            String sPostData = "accesskey=" + sAK +
                    "&from=" + sIdSumber +
                    "&to=" + sIdHasil +
                    "&text=" + URLEncoder.encode(sTeksSumber, StandardCharsets.UTF_8);
            HttpURLConnection hurlcHub = (HttpURLConnection) urlServer.openConnection();
            hurlcHub.setRequestMethod("POST");
            hurlcHub.setDoOutput(true);
            hurlcHub.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            hurlcHub.setRequestProperty("Content-Length", Integer.toString(sPostData.length()));
            hurlcHub.setUseCaches(false);
            try (DataOutputStream dosOut = new DataOutputStream(hurlcHub.getOutputStream())) {
                dosOut.writeBytes(sPostData);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            try (BufferedReader brIsi = new BufferedReader(new InputStreamReader(
                    hurlcHub.getInputStream(), StandardCharsets.UTF_8))) {
                String sBaris;
                while ((sBaris = brIsi.readLine()) != null) {
                    if ("\f\b".equals(sBaris.substring(0, 2))) {
                        String[] sParseError = sBaris.split("\\|");
                        switch (sParseError[1]) {
                            case "00":
                                sHasil.append("ERROR : Engine error, please contact developer.\n").append(sParseError[2]);
                                break;
                            case "01":
                                sHasil.append("ERROR : Please obtain Access Key.\n\nOPTIONS > MENU > Access Key");
                                break;
                            case "02":
                                sHasil.append("ERROR : Access Key is not valid.\nPlease obtain a new one.\n\nOPTIONS > MENU > Access Key");
                                break;
                            case "03":
                                sHasil.append("ERROR : Server is under maintenance, please try again later.");
                                break;
                            default:
                                sHasil.append("ERROR : Unknown error, please contact developer (Code : ").append(sParseError[2]).append(").");
                                break;
                        }
                    } else {
                        sHasil.append(sBaris);
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } catch (Exception e) {
            bTextTerjemah.setText("TRANSLATE");
            String errmsg = (e.getMessage().contains(": 500")) ? "WARNING !\nThe server issues an exception indicating that you are translating too many times in short time. As a result, your IP has been temporarily blocked by Google.\n\nPlease try again 10 minutes later, if this message still appears, please try again every 10 to 20 minutes.\n\nUsually, this IP blocking is a maximum of 1 day " : "The following error occured :\n" + e.getMessage();
            sHasil = new StringBuilder(errmsg);
            Toolkit.getDefaultToolkit().beep();
        }
        bTextTerjemah.setText("TRANSLATE");
        bTextTerjemah.setEnabled(true);
        return sHasil.toString();
    }

    Color onContrastColor(Color color) {
        int y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }

    void doIsiBahasa() {
        for (String sBhs : sBhsNama) {
            cmbTextHasil.addItem(sBhs);
            cmbTextSumber.addItem(sBhs);
        }
        cmbTextSumber.setSelectedIndex(21);
        cmbTextHasil.setSelectedIndex(43);
        iLastTextSumber = 21;
        iLastTextHasil = 43;
        iLastSubSumber = 21;
        iLastSubHasil = 43;
    }

    void doSwap() {
        int iTempSumber = cmbTextSumber.getSelectedIndex();
        int iTempHasil = cmbTextHasil.getSelectedIndex();
        cmbTextSumber.setSelectedIndex(iTempHasil);
        cmbTextHasil.setSelectedIndex(iTempSumber);
        doCmbChange(true);
        doCmbChange(false);
    }

    void doCmbChange(boolean bIsSumber) {
        if (cmbTextHasil.getSelectedIndex() == cmbTextSumber.getSelectedIndex()) {
            ((bIsSumber) ? cmbTextHasil : cmbTextSumber).setSelectedIndex((bIsSumber) ? iLastTextSumber : iLastTextHasil);
        }
        iLastTextSumber = cmbTextSumber.getSelectedIndex();
        iLastTextHasil = cmbTextHasil.getSelectedIndex();

        config(true, "BahasaSumber", String.valueOf(iLastTextSumber));
        config(true, "BahasaHasil", String.valueOf(iLastTextHasil));
    }

    void doCTC() {
        try {
            Thread.sleep(250);
        } catch (Exception ignored) {
        }
        bOnTransClipboard = true;
        StringSelection strSel = new StringSelection(taTextHasil.getText());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
    }

    void doTerjemah() {
        if (cmbTextSumber.getSelectedIndex() == cmbTextHasil.getSelectedIndex()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Unable to perform translation.\nSource and Destination Language must be different.", "Identic Language", JOptionPane.ERROR_MESSAGE);
        } else {
            if (taTextSumber.getText().length() > 2) {
                String sHasil = onTrans(taTextSumber.getText(), sBhsId[cmbTextSumber.getSelectedIndex()], sBhsId[cmbTextHasil.getSelectedIndex()]);
                taTextHasil.setText(sHasil);
                if (bKTT) {
                    config(true, "IsiSumber", taTextSumber.getText());
                    config(true, "IsiHasil", sHasil);
                }
                if (bSetClipboard) {
                    //meski begitu, doCTC tidak bisa bekerja jika "Auto Copy from Clipboard" aktif.
                    doCTC();
                }
                if (bOnTransClipboard) {
                    bOnTransClipboard = false;
                }
                bTextTerjemah.setText("TRANSLATE");
                bTextTerjemah.setEnabled(true);
            }
        }
    }

    void doAOT() {
        boolean bAOT = miAOT.getState();
        miAOT.setState(bAOT);
        setAlwaysOnTop(bAOT);
        config(true, "SelaluDiatas", String.valueOf(bAOT));
    }

    void doReset(boolean bConfirm) {
        Toolkit.getDefaultToolkit().beep();
        boolean bOpsi = false;
        if (bConfirm) {
            bOpsi = JOptionPane.showConfirmDialog(this, "All current configuration will be deleted. Are you sure?", "Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
        }
        if (bOpsi) {
            Properties pSetelan = new Properties();
            try {
                pSetelan.setProperty("ResetPada", new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date()));
                FileOutputStream fos = new FileOutputStream(sCurrentName + ".cimo");
                pSetelan.storeToXML(fos, "JGoTranLite Config Data");
            } catch (Exception ignored) {
            }
            taTextSumber.setText("");
            taTextHasil.setText("");
            config(false, null, null);
        }
    }

    void doClipboard(boolean bSet) {
        if (bSet) {
            bSetClipboard = !bSetClipboard;
        } else {
            bGetClipboard = !bGetClipboard;
        }

        ((bSet) ? miACTC : miACFC).setState((bSet) ? bSetClipboard : bGetClipboard);
        config(true, "AutoSalinKlip" + ((bSet) ? "Ke" : "Dari"), String.valueOf((bSet) ? bSetClipboard : bGetClipboard));
    }

    void doKTT() {
        bKTT = !bKTT;
        miKTT.setState(bKTT);
        config(true, "SimpanTeks", String.valueOf(bKTT));
    }

    void doFont(boolean isSumber) {
        try {
            if (miAOT.getState()) {
                setAlwaysOnTop(false);
            }
            FontDialog.showDialog((isSumber) ? taTextSumber : taTextHasil);
            String[] sFontCfg = {
                    ((isSumber) ? taTextSumber : taTextHasil).getFont().getFontName(),
                    String.valueOf(((isSumber) ? taTextSumber : taTextHasil).getFont().getStyle()),
                    String.valueOf(((isSumber) ? taTextSumber : taTextHasil).getFont().getSize()),
            };
            config(true, "Font" + ((isSumber) ? "Sumber" : "Hasil"), Arrays.toString(sFontCfg));
            if (miAOT.getState()) {
                setAlwaysOnTop(true);
            }
        } catch (Exception e) {
            System.exit(69);
        }
    }

    void doColor(boolean isSumber) {
        if (miAOT.getState()) {
            setAlwaysOnTop(false);
        }
        Color jccLatar = JColorChooser.showDialog(null, "Select " + ((isSumber) ? "Source" : "Result") + " Text Background Color", ((isSumber) ? taTextSumber : taTextHasil).getBackground());
        if (jccLatar != null) {
            ((isSumber) ? taTextSumber : taTextHasil).setBackground(jccLatar);
            ((isSumber) ? taTextSumber : taTextHasil).setForeground(onContrastColor(jccLatar));
            ((isSumber) ? taTextSumber : taTextHasil).setSelectedTextColor(jccLatar);
            ((isSumber) ? taTextSumber : taTextHasil).setSelectionColor(onContrastColor(jccLatar));
            ((isSumber) ? taTextSumber : taTextHasil).setCaretColor(onContrastColor(jccLatar));
            config(true, "WarnaLatar" + ((isSumber) ? "Sumber" : "Hasil"), String.valueOf(((isSumber) ? taTextSumber : taTextHasil).getBackground().getRGB()));
        }
        if (miAOT.getState()) {
            setAlwaysOnTop(true);
        }
    }

    void doClear() {
        Toolkit.getDefaultToolkit().beep();
        if (JOptionPane.showConfirmDialog(this, "Do you want to clear all Source and Result Text contents?", "Clear All", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            taTextSumber.setText("");
            taTextHasil.setText("");
        }
    }

    void doActivate() {
        Object[] obBtn = {"Continue", "Get Access Key", "Cancel"};
        int iInfoAct = JOptionPane.showOptionDialog(this,
                "Access Key is a code that is required every time you do a translation.\n" +
                        "Access Key will be updated regularly.\n\n" +
                        "If you get the message \"Access Key is invalid.\",\nthat means you need to update the Access Key.\n\n" +
                        "To obtain and/or update the Access Key, please click \"Get Access Key\".\n"+
                        "Or, click \"Continue\" to enter the Access Key, so you can do the translation.\n   ",
                "Access Key", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, obBtn, null);
        switch (iInfoAct) {
            case JOptionPane.YES_OPTION:
                String sCodeAct = JOptionPane.showInputDialog(this, "Please paste your Access Key below, and click OK.", "Access Key", JOptionPane.WARNING_MESSAGE);
                if (sCodeAct.length() > 1) {
                    sAK = sCodeAct;
                    config(true, "KunciAkses", sAK);
                }
                break;
            case JOptionPane.NO_OPTION:
                Desktop deskOut = java.awt.Desktop.getDesktop();
                URI uriOut = null;
                try {
                    uriOut = new URI("https://gotran.cimosoft.com/j/getAccessKey");
                } catch (Exception ignored) {
                }
                try {
                    deskOut.browse(uriOut);
                } catch (Exception ignored) {
                }
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }

    void doAbout() {
        boolean bAOTState = isAlwaysOnTop();
        setAlwaysOnTop(true);
        final ImageIcon iiIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("ikon.png")));
        JOptionPane.showMessageDialog(jfUtama.this,
                "JGoTranLite is a simple translator, which uses the Google Translate service to do the translation.\n" +
                        "JGoTranLite is a cross-platform yet open-source version of GoTran, the same program built for Windows.\n\n" +
                        "So that your IP is not temporarily blocked by Google Translate,\nalways give pause of at least 5 (five) seconds each translation.\n\n" +
                        "Im using my own server to bridge this tool with Google Translate.\n" +
                        "Since that, i need to make sure my tiny server is not overloaded, so i may frequently change the Access Key.\n" +
                        "Changing the Access Key might also change the rules of the translation, according to server condition.\n\n" +
                        "If you're tired of obtaining Access Key,\nyou can use GoTran instead (Windows only) on https://gotran.cimosoft.com/.\n\n" +
                        "But, if you find this tool useful, please consider share it.\n\n" +
                        "Programmer : Arachmadi Putra (https://me.cimosoft.com | Indonesian Only)\n\n" +
                        "Q : Why \"Lite\"?\nA : Because its only contains Text Translator, while the non-Lite version is also contain Subtitle Translator.\n" +
                        "Q : When the non-Lite source version release?\nA : This version is not for end-user actually, since its just for my internal team translation projects.\n   ",
                "About JGoTranLite " + sVer, JOptionPane.INFORMATION_MESSAGE, iiIcon);
        setAlwaysOnTop(bAOTState);
    }

    void config(boolean bSave, String sKey, String sVal) {
        try {
            Properties pSetelan = new Properties();
            if (!(new File(sCurrentName + ".cimo")).exists()) {
                FileOutputStream fos = new FileOutputStream(sCurrentName + ".cimo");
                pSetelan.storeToXML(fos, "JGoTranLite Config Data");
                fos.close();
            }
            FileInputStream fis = new FileInputStream(sCurrentName + ".cimo");
            //System.out.println(sCurrentPath+"/config.cimo");
            try {
                pSetelan.loadFromXML(fis);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            if (bSave) {
                try {
                    pSetelan.setProperty(sKey, sVal);
                    FileOutputStream fos = new FileOutputStream(sCurrentName + ".cimo");
                    pSetelan.storeToXML(fos, "JGoTranLite Config Data");
                } catch (Exception ignored) {
                }
            } else {
                //disini loop variabel
                //menu state
                miAOT.setState(Boolean.parseBoolean(pSetelan.getProperty("SelaluDiatas", String.valueOf(false))));
                setAlwaysOnTop(miAOT.getState());
                //text translation
                try {
                    iLastTextSumber = Integer.parseInt(pSetelan.getProperty("BahasaSumber", "43"));
                } catch (Exception e) {
                    iLastTextSumber = 43;
                }
                cmbTextSumber.setSelectedIndex(iLastTextSumber);
                try {
                    iLastTextHasil = Integer.parseInt(pSetelan.getProperty("BahasaHasil", "21"));
                } catch (Exception e) {
                    iLastTextHasil = 21;
                }
                cmbTextHasil.setSelectedIndex(iLastTextHasil);
                String[] sTempFontS = pSetelan.getProperty("FontSumber", "Consolas,0,14").replaceAll("^\\[|]$", "").split(",");
                taTextSumber.setFont(new Font(sTempFontS[0], Integer.parseInt(sTempFontS[1].trim()), Integer.parseInt(sTempFontS[2].trim())));
                Color cTempFontS;
                try {
                    cTempFontS = Color.decode(pSetelan.getProperty("WarnaLatarSumber", "0"));
                } catch (Exception e) {
                    cTempFontS = Color.decode("0");
                }
                taTextSumber.setBackground(cTempFontS);
                taTextSumber.setForeground(onContrastColor(cTempFontS));
                taTextSumber.setSelectedTextColor(cTempFontS);
                taTextSumber.setSelectionColor(onContrastColor(cTempFontS));
                taTextSumber.setCaretColor(onContrastColor(cTempFontS));
                String[] sTempFontH = pSetelan.getProperty("FontHasil", "Consolas,0,14").replaceAll("^\\[|]$", "").split(",");
                taTextHasil.setFont(new Font(sTempFontH[0], Integer.parseInt(sTempFontH[1].trim()), Integer.parseInt(sTempFontH[2].trim())));
                Color cTempFontH;
                try {
                    cTempFontH = Color.decode(pSetelan.getProperty("WarnaLatarHasil", "-16777088"));
                } catch (Exception e) {
                    cTempFontH = Color.decode("-16777088");
                }
                taTextHasil.setBackground(cTempFontH);
                taTextHasil.setForeground(onContrastColor(cTempFontH));
                taTextHasil.setSelectedTextColor(cTempFontH);
                taTextHasil.setSelectionColor(onContrastColor(cTempFontH));
                taTextHasil.setCaretColor(onContrastColor(cTempFontH));
                bSetClipboard = Boolean.parseBoolean(pSetelan.getProperty("AutoSalinKlipKe", String.valueOf(false)));
                miACTC.setState(bSetClipboard);
                bGetClipboard = Boolean.parseBoolean(pSetelan.getProperty("AutoSalinKlipDari", String.valueOf(false)));
                miACFC.setState(bGetClipboard);
                bKTT = Boolean.parseBoolean(pSetelan.getProperty("SimpanTeks", String.valueOf(false)));
                miKTT.setState(bKTT);
                if (bKTT) {
                    taTextSumber.setText(pSetelan.getProperty("IsiSumber", ""));
                    taTextHasil.setText(pSetelan.getProperty("IsiHasil", ""));
                }
                sAK = pSetelan.getProperty("KunciAkses", "");
            }
        } catch (Exception ex) {
            doReset(false);
            try {
                (new File(sCurrentName + ".cimo")).delete();
            } catch (Exception ey) {
                Toolkit.getDefaultToolkit().beep();
                String[] sSubFN = sCurrentName.replace("\\", "/").split("/");
                JOptionPane.showMessageDialog(this, "Unable to " + ((bSave) ? "save configuration." : "load configuration. The default configuration loaded") + ".\nIf this error persist, please delete \"" + sSubFN[sSubFN.length - 1] + ".cimo\", and restart JGoTranLite.", "Configuration Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void onCopy(String data) {
        if (bGetClipboard && !bOnTransClipboard && !data.equals(taTextHasil.getText())) {
            taTextSumber.setText(data);
            doTerjemah();
        }
    }

    void initEvents() {
        doIsiBahasa();
        jcClipboard jccLatar = new jcClipboard();
        jccLatar.setWadah(this);
        jccLatar.start();
        miAOT.addActionListener(e -> doAOT());
        miReset.addActionListener(e -> doReset(true));
        miActivate.addActionListener(e -> doActivate());
        miAbout.addActionListener(e -> doAbout());
        cmbTextSumber.addActionListener(e -> doCmbChange(true));
        cmbTextHasil.addActionListener(e -> doCmbChange(false));
        bTextGanti.addActionListener(e -> doSwap());
        bTextTerjemah.addActionListener(e -> doTerjemah());
        bTextOpsi.addActionListener(e -> pmTextOpsi.show(bTextOpsi, 0, 24));
        miACTC.addActionListener(e -> doClipboard(true));
        miACFC.addActionListener(e -> doClipboard(false));
        miKTT.addActionListener(e -> doKTT());
        miSF.addActionListener(e -> doFont(true));
        miSB.addActionListener(e -> doColor(true));
        miRF.addActionListener(e -> doFont(false));
        miRB.addActionListener(e -> doColor(false));
        miCA.addActionListener(e -> doClear());
        Document docSumber = taTextSumber.getDocument();
        final UndoManager um = new UndoManager();
        docSumber.addUndoableEditListener(e -> um.addEdit(e.getEdit()));
        taTextSumber.getActionMap().put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (um.canUndo()) {
                    um.undo();
                }
            }
        });
        taTextSumber.getActionMap().put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (um.canRedo()) {
                    um.redo();
                }
            }
        });
        taTextSumber.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
        taTextSumber.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        taTextSumber.setComponentPopupMenu(pmTextCM);
        taTextSumber.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    miSelectall.setEnabled(taTextSumber.getText().length() != 0);
                    miClear.setEnabled(taTextSumber.getSelectedText().length() != 0);
                    miCopy.setEnabled(taTextSumber.getSelectedText().length() != 0);
                    miCut.setEnabled(taTextSumber.getSelectedText().length() != 0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        taTextHasil.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            taTextHasil.selectAll();
                            if (bSetClipboard) {
                                doCTC();
                            }
                        }
                    }
                }
        );
        miCut.addActionListener(e -> ((JTextPane) pmTextCM.getInvoker()).cut());
        miCopy.addActionListener(e -> ((JTextPane) pmTextCM.getInvoker()).copy());
        miPaste.addActionListener(e -> ((JTextPane) pmTextCM.getInvoker()).paste());
        miClear.addActionListener(e -> ((JTextPane) pmTextCM.getInvoker()).replaceSelection(""));
        miSelectall.addActionListener(e -> ((JTextPane) pmTextCM.getInvoker()).selectAll());
        config(false, null, null);
    }

}
