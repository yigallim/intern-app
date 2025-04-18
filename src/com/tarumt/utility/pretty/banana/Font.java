package com.tarumt.utility.pretty.banana;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.map.SimpleHashMap;

public class Font {
    public static final Font ANSI_SHADOW;
    public static final Font STANDARD;

    protected static final ListInterface<Font> VALUES;
    protected static final SimpleHashMap<String, Font> MAP;
    protected static final String ROOT_DIR_PATH = "src/resources/banana";
    protected static final String FONT_DIR_PATH = ROOT_DIR_PATH + "/fonts/";

    static {

        ListInterface<Font> values = new DoublyLinkedList<>();
        values.add(STANDARD = new Font("Standard", "Standard.flf"));
        values.add(ANSI_SHADOW = new Font("ANSI Shadow", "ANSI_Shadow.flf"));

        SimpleHashMap<String, Font> map = new SimpleHashMap<>(values.size());
        for (Font v : values) {
            map.put(v.name, v);
        }

        VALUES = values;
        MAP = map;
    }

    protected final String name;
    protected final String filename;
    protected final Charset charset;

    protected Font(String name) {
        this.name = name;
        this.filename = null;
        this.charset = StandardCharsets.UTF_8;
    }

    protected Font(String name, String filename) {
        this.name = name;
        this.filename = filename;
        this.charset = StandardCharsets.UTF_8;
    }

    protected Font(String name, String filename, Charset charset) {
        this.name = name;
        this.filename = filename;
        this.charset = charset;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public Charset getCharset() {
        return charset;
    }

    public InputStream getInputStream() throws IOException {
        String filePath = FONT_DIR_PATH + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Failed to load font '" + this.name + "', the specified font does not exist at: " + filePath);
        }
        return convertIfZipped(Files.newInputStream(file.toPath()));
    }

    protected static InputStream convertIfZipped(InputStream inputStream) throws IOException {

        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        if (isZipped(bufferedInputStream)) {

            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
            ZipEntry entry = zipInputStream.getNextEntry();
            if (entry == null) {
                throw new RuntimeException("Failed to convert the InputStream.");
            }
            return zipInputStream;
        } else {
            return bufferedInputStream;
        }
    }

    protected static boolean isZipped(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] buf = new byte[4];
        bufferedInputStream.mark(4);
        int bytesRead = bufferedInputStream.read(buf); // Check how many bytes were actually read
        bufferedInputStream.reset();

        // Ensure 4 bytes were read before comparing
        if (bytesRead < 4) {
            return false;
        }

        // Manual byte array comparison for ZIP magic number (PK\003\004)
        return buf[0] == 0x50 && buf[1] == 0x4b && buf[2] == 0x03 && buf[3] == 0x04;
    }

    public static ListInterface<Font> values() {
        return VALUES;
    }

    public static Font get(String name) {
        return MAP.get(name);
    }

    public static Font getOrDefault(String name, Font defaultValue) {

        return MAP.getOrDefault(name, defaultValue);
    }

}