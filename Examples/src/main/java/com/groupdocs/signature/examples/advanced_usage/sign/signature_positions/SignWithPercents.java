package com.groupdocs.signature.examples.advanced_usage.sign.signature_positions;


import com.groupdocs.signature.Signature;
import com.groupdocs.signature.domain.Padding;
import com.groupdocs.signature.domain.SignResult;
import com.groupdocs.signature.domain.barcodes.BarcodeTypes;
import com.groupdocs.signature.domain.enums.MeasureType;
import com.groupdocs.signature.domain.signatures.BaseSignature;
import com.groupdocs.signature.examples.Constants;
import com.groupdocs.signature.exception.GroupDocsSignatureException;
import com.groupdocs.signature.options.sign.BarcodeSignOptions;

import java.io.File;
import java.nio.file.Paths;

public class SignWithPercents {
    /**
     * Sign document with Bar-Code signature applying specific options
     */
    public static void run() throws Exception
    {
        // The path to the documents directory.
        String filePath = Constants.SAMPLE_PDF;
        String fileName = Paths.get(filePath).getFileName().toString();

        String outputFilePath = new File(Constants.OutputPath, "SignWithPercents\\" + fileName).getPath();

        try {
            Signature signature = new Signature(filePath);
            // create barcode option with predefined barcode text
            BarcodeSignOptions options = new BarcodeSignOptions("12345678");

            // setup Barcode encoding type
            options.setEncodeType(BarcodeTypes.Code128);


            // set signature position in absolute position
            options.setLocationMeasureType(MeasureType.Percents);
            options.setLeft(5);
            options.setTop(5);

            // set signature area in millimeters
            options.setSizeMeasureType(MeasureType.Percents);
            options.setWidth(10);
            options.setHeight(5);

            // set margin in millimeters
            options.setMarginMeasureType(MeasureType.Percents);
            Padding padding = new Padding();
            padding.setLeft(1);
            padding.setTop(1);
            padding.setRight(1);
            options.setMargin(padding);

            // sign document to file
            SignResult signResult = signature.sign(outputFilePath, options);
            // analyzing result
            System.out.print("List of newly created signatures:");
            int number = 1;
            for(BaseSignature temp : signResult.getSucceeded())
            {
                System.out.print("Signature #"+ number++ +": Type: "+temp.getSignatureType()+" Id:"+temp.getSignatureId()+
                        ",Location: "+temp.getLeft()+"x"+temp.getTop()+". Size: "+temp.getWidth()+"x"+temp.getHeight());
            }
            System.out.print("\nSource document signed successfully.\nFile saved at " + outputFilePath);
        }catch(Exception e){
            throw new GroupDocsSignatureException(e.getMessage());
        }
    }
}