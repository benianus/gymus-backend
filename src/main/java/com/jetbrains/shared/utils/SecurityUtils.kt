package com.jetbrains.shared.utils

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository
import com.jetbrains.gymusserverjava.memberships.repositories.MemberRepository
import com.jetbrains.gymusserverjava.store.repositories.ProductRepository
import com.jetbrains.shared.exceptions.CustomExceptionHandler.Companion.resourceNotFound
import com.jetbrains.shared.exceptions.InvalidIdException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SecurityUtils(
    private val userRepository: UserRepository,
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository,
    private val helpers: Helpers
) {
    fun isMemberOwner(memberId: Int): Boolean {
        if (memberId <= 0) {
            throw InvalidIdException("invalid memberId: $memberId")
        }

        val userDetails = helpers.getUserDetails()

        val user = userRepository.findByUsername(userDetails.username)
            .orElseThrow { resourceNotFound("user not found") }

        val member = memberRepository.findById(memberId)
            .orElseThrow { throw resourceNotFound("member not found") }

        return user.id == member.user
    }

    fun isProductOwner(productId: Int): Boolean {
        if (productId <= 0) {
            throw InvalidIdException("invalid productId: $productId")
        }

        val userDetails = helpers.getUserDetails()

        val user = userRepository.findByUsername(userDetails.username)
            .orElseThrow { throw resourceNotFound("user not found") }

        val product = productRepository.findByIdOrNull(productId)
            ?: throw resourceNotFound("product not found")

        return user.id == product.user
    }
}
