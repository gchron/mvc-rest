package com.myjetbrains.cronix.bootstrap;

import com.myjetbrains.cronix.domain.Category;
import com.myjetbrains.cronix.domain.Customer;
import com.myjetbrains.cronix.repositories.CategoryRepository;
import com.myjetbrains.cronix.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();

        loadCategories();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstname("Dexter");
        customer.setLastname("Morgan");
        customer.setCustomerUrl("/api/v1/customer/" + customer.getId());
        customerRepository.save(customer);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("San");
        customer2.setLastname("Andreas");
        customer2.setCustomerUrl("/api/v1/customer/" + customer2.getId());
        customerRepository.save(customer2);

        System.out.println("Customers loaded " + customerRepository.count());
    }
}