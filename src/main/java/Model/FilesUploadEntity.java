package Model;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by DELL on 5/16/2017.
 */
@Entity
@Table(name = "FILES_UPLOAD", schema = "SDPROJECT", catalog = "")
public class FilesUploadEntity {
    private long uploadId;
    private String fileName;
    private byte[] fileData;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_GENERATOR")
    @SequenceGenerator(name = "ID_GENERATOR", sequenceName = "SEQ_FILE_UPLOAD")
    @Column(name = "UPLOAD_ID", nullable = false, precision = 0)
    public long getUploadId() {
        return uploadId;
    }

    public void setUploadId(long uploadId) {
        this.uploadId = uploadId;
    }

    @Basic
    @Column(name = "FILE_NAME", nullable = true, length = 128)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "FILE_DATA", nullable = true)
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesUploadEntity that = (FilesUploadEntity) o;

        if (uploadId != that.uploadId) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (!Arrays.equals(fileData, that.fileData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uploadId ^ (uploadId >>> 32));
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(fileData);
        return result;
    }
}
