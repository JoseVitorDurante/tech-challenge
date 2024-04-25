package br.com.techChallenge.core.domain.product;

import br.com.techChallenge.core.domain.category.CategoryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDomain {
    private UUID id;
    private String name;
    private BigDecimal price;
    private UUID idCategory;
    private CategoryDomain category;
}
