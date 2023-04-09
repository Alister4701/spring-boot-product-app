package io.codefountain.spring.product.qr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import io.codefountain.spring.product.domain.Product;

@Service
public class QRCodeService {

	public List<Product> populateQrForProducts(List<Product> productList) throws WriterException, IOException {

		List<Product> productListWithQr = new ArrayList<>();
		byte[] image = new byte[0];
		
		for (Product product : productList) {
			String qrText =product.getId()+","+product.getName()+","+product.getQuantity()+","+product.getPrice();
			image = QRCodeGenerator.getQRCodeImage(qrText, 250, 250);
			String qrcode = Base64.getEncoder().encodeToString(image);
			
			Product productWithQr = new Product();
			productWithQr.setId(product.getId());
			productWithQr.setName(product.getName());
			productWithQr.setQuantity(product.getQuantity());
			productWithQr.setPrice(product.getPrice());
			productWithQr.setQrCode(qrcode);
			productListWithQr.add(productWithQr);

		}

		return productListWithQr;

	}

}
