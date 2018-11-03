package com.tcfritchman.write;

import com.tcfritchman.pojo.Release;

/**
 * @author Thomas Fritchman
 */
public class ReleaseNodeWriter extends Neo4jImportFileWriter<Release> {

    private static final String TITLE = "releases";
    private static final String[] HEADERS = {"id:ID", "title", "released"};

    public ReleaseNodeWriter() {
        super(TITLE, HEADERS, (t -> new String[]{
                t.getId(),
                t.getTitle(),
                t.getReleased()
        }));
    }

    @Override
    public void accept(Release release) {
        write(release);
    }
}
