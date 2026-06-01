package com.jetbrains.shared.utils

import com.jetbrains.gymusserverjava.auth.entities.User
import com.jetbrains.gymusserverjava.auth.repositories.UserRepository
import com.jetbrains.gymusserverjava.memberships.repositories.MemberRepository
import com.jetbrains.gymusserverjava.store.repositories.ProductRepository
import com.jetbrains.shared.exceptions.CustomExceptionHandler.Companion.resourceNotFound
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class SecurityUtils(
    private val userRepository: UserRepository,
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository,
) {
    fun isMemberOwner(memberId: Int): Boolean {
        isValidId(memberId)
        val userDetails = getUserDetails()

        val user = findUser(userDetails)

        val member = memberRepository.findById(memberId)
            .orElseThrow { throw resourceNotFound("member not found") }

        return user.id == member.user
    }


    fun isProductOwner(productId: Int): Boolean {
        isValidId(productId)
        val userDetails = getUserDetails()

        val user = findUser(userDetails)

        val product = productRepository.findByIdOrNull(productId)
            ?: throw resourceNotFound("product not found")

        return user.id == product.user
    }

    private fun findUser(userDetails: UserDetails): User =
        userRepository.findByUsername(userDetails.username)
            .orElseThrow { resourceNotFound("user not found") }
}
