package com.snp.userservice.service.join;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.join.request.JoinRequestDto;

public interface JoinService {

    ApiResponseDto joinProgress(JoinRequestDto requestDto);

}
