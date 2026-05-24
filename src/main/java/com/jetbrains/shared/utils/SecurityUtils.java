package com.jetbrains.shared.utils;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.gymusserverjava.memberships.repositories.MemberRepository;
import com.jetbrains.gymusserverjava.store.repositories.ProductRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import com.jetbrains.shared.exceptions.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Helpers helpers;

    public boolean isMemberOwner(int memberId) {
        if(memberId <= 0) {
            throw new InvalidIdException("invalid memberId: " + memberId);
        }

        var userDetails = helpers.getUserDetails();

        var user = userRepository.findByUsername(userDetails.getUsername())
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));

        var member = memberRepository.findById(memberId)
                                     .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                             "member not found"));
        return user.getId() == member.getUser();

    }

    public boolean isProductOwner(int productId) {
        if(productId <= 0) {
            throw new InvalidIdException("invalid productId: " + productId);
        }

        var userDetails = helpers.getUserDetails();

        var user = userRepository.findByUsername(userDetails.getUsername())
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));

        var product = productRepository.findById(productId)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        return user.getId() == product.getUser();
    }

}
