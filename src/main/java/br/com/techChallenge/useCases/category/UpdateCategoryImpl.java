package br.com.techChallenge.useCases.category;

import br.com.techChallenge.useCases.category.exceptions.CategoryNotFound;
import br.com.techChallenge.domain.entity.category.CategoryDomain;
import br.com.techChallenge.domain.port.category.CategoryPersistencePort;
import br.com.techChallenge.domain.useCases.category.CreateNewCategory;
import br.com.techChallenge.domain.useCases.category.UpdateCategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryImpl implements UpdateCategory {

    private final CategoryPersistencePort categoryPersistencePort;
    private final ModelMapper modelMapper;
    private final CreateNewCategory createNewCategory;
    @Override
    public CategoryDomain execute(CategoryDomain updateCategoryDomain) {
        CategoryDomain domain = categoryPersistencePort.findById(updateCategoryDomain.getId())
                .orElseThrow(CategoryNotFound::new);

        modelMapper.map(updateCategoryDomain, domain);

        return createNewCategory.execute(domain);
    }
}