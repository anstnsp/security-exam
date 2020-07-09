package com.example.anstn.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하게함.
@EntityListeners(AuditingEntityListener.class) // 해당클래스(BaseTimeEnitty)에 Auditing 기능을 포함시킴.
public class BaseTimeEntity {
  @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장됨.
  private LocalDateTime createdDate;

  @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장.
  private LocalDateTime modifiedDate;
}