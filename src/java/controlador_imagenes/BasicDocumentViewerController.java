/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_imagenes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.event.ComponentSystemEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author Elena
 */
@Named
@SessionScoped
public class BasicDocumentViewerController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String downloadFileName = "pfe-rocks.pdf";
    private StreamedContent content;

    public void onPrerender(final ComponentSystemEvent event) {

//        try {
//
//            final ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//            content = DefaultStreamedContent.builder().stream(() -> new ByteArrayInputStream(selected ))
//                        .contentType("application/pdf").build();
//        }
//        catch (final Exception e) {
//
//        }
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(final StreamedContent content) {
        this.content = content;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(final String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }
}
