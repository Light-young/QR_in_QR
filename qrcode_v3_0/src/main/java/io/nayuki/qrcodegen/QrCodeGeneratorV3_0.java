/* 
 * QR Code generator demo (Java)
 * 
 * Run this command-line program with no arguments. The program creates/overwrites a bunch of
 * PNG and SVG files in the current working directory to demonstrate the creation of QR Codes.
 * 
 * Copyright (c) Project Nayuki. (MIT License)
 * https://www.nayuki.io/page/qr-code-generator-library
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * - The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 * - The Software is provided "as is", without warranty of any kind, express or
 *   implied, including but not limited to the warranties of merchantability,
 *   fitness for a particular purpose and noninfringement. In no event shall the
 *   authors or copyright holders be liable for any claim, damages or other
 *   liability, whether in an action of contract, tort or otherwise, arising from,
 *   out of or in connection with the Software or the use or other dealings in the
 *   Software.
 */

package io.nayuki.qrcodegen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;


public final class QrCodeGeneratorV3_0 {
	
	// The main application program.
	public static void main(String[] args) throws IOException {
		doQRCode();
	}
		
	/*---- Demo suite ----*/
	
	// Creates a single QR Code, then writes it to a PNG file and an SVG file.
	private static void doQRCode() throws IOException {
		String originaltext = "This is the big one.";          // User-supplied Unicode text
		QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;  // Error correction level
		
		QrCode qr = QrCode.encodeText(originaltext, errCorLvl);  // Make the QR Code symbol
		for (int x = 0; x <= 6; x++)
			for (int y = 0; y <= 6; y++){
				qr.BitFlip (0, y);
			}
		BufferedImage img = qr.toImage(10, 4);           // Convert to bitmap image
		writePng(img, "QR.png");
		qr.SetisFunctionFree();

	}
	
	
	/*---- Utilities ----*/
	
	// Helper function to reduce code duplication.
	private static void writePng(BufferedImage img, String filepath) throws IOException {
		ImageIO.write(img, "png", new File(filepath));
	}
	
}
