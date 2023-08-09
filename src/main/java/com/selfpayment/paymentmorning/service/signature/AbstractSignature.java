package com.selfpayment.paymentmorning.service.signature;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.examples.signature.SigUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SigningSupport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public abstract class AbstractSignature extends SignaturePdfLoader {
    protected abstract File getPdfFile();

    protected abstract File getOutputFile();

    protected abstract Object signInfo();

    @Override
    protected PDDocument loadPdf() {
        try (var document = Loader.loadPDF(this.getPdfFile())) {
            return document;
        } catch (IOException e) {
            throw new RuntimeException("Unable to load pdf file", e);
        }
    }

    @Override
    public void sign() throws IOException {
        this.validatePDDocument();
        var document = this.loadPdf();
        this.validateAccessPermission(document);
        var signature = this.createPDSignature();
        SignatureOptions signatureOptions = new SignatureOptions();
        signatureOptions.setPreferredSignatureSize(SignatureOptions.DEFAULT_SIGNATURE_SIZE * 2);
        document.addSignature(signature, null, signatureOptions);
        document.saveIncremental(new ByteArrayOutputStream());

    }

    private void validateAccessPermission(PDDocument document) {
        var accessPermissionSign = SigUtils.getMDPPermission(document);
        if (accessPermissionSign == 1) {
            throw new IllegalStateException("No changes to the document are permitted due to DocMDP transform parameters dictionary");
        }
    }

    private PDSignature createPDSignature() {
        var signature = new PDSignature();
        signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        signature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        signature.setName("Example User");
        signature.setLocation("Los Angeles, CA");
        signature.setReason("Testing");
        return signature;
    }


}
