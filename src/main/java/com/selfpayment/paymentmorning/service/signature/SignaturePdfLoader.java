package com.selfpayment.paymentmorning.service.signature;

import org.apache.pdfbox.pdmodel.PDDocument;

public abstract class SignaturePdfLoader implements Signature {
    protected abstract PDDocument loadPdf();

    protected void validatePDDocument() {
        if (this.loadPdf() == null) {
            throw new IllegalArgumentException("PDDocument must be not null!");
        }
    }
}
