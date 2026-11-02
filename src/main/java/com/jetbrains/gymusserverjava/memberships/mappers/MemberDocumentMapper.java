package com.jetbrains.gymusserverjava.memberships.mappers;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.memberships.dtos.requests.RegisterMemberRequestDto;
import com.jetbrains.gymusserverjava.memberships.entities.Member;
import com.jetbrains.gymusserverjava.memberships.entities.MemberDocument;
import com.jetbrains.shared.fileStorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MemberDocumentMapper {

    @Autowired
    private FileStorageService fileStorageService;

    public MemberDocument toEntity(
            RegisterMemberRequestDto dto,
            Member member,
            User user
    ) {

        // upload files
        var medicalCertificate = fileStorageService.uploadFile(dto.medicalCertificate);
        var birthdateCertificate = fileStorageService.uploadFile(dto.birthCertificate);
        var personalPhoto = fileStorageService.uploadFile(dto.personalPhoto);
        var parentalAuthorization = fileStorageService.uploadFile(dto.parentalAuthorization);

        var entity = new MemberDocument();

        entity.setMedicalCertificate(medicalCertificate);
        entity.setBirthCertificate(birthdateCertificate);
        entity.setPersonalPhoto(personalPhoto);
        entity.setParentalAuthorization(parentalAuthorization);
        entity.setMember(member);
        entity.setUser(user);

        return entity;
    }

}
