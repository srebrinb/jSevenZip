/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bobs.is.compress.sevenzip;

/**
 *
 * @author sbalabanov
 */
import java.util.ArrayList;
import java.util.List;


public class Block {
    private final List<Long> unpackSizes = new ArrayList<Long>();
    private final List<SevenZArchiveEntry> entries = new ArrayList<SevenZArchiveEntry>();

    public void addUnpackSize(long size) {
        unpackSizes.add(size);
    }

    public List<Long> getUnpackSizes() {
        return unpackSizes;
    }

    public List<SevenZArchiveEntry> getEntries() {
        return entries;
    }
}
