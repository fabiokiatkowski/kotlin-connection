package com.example.oreonconnection.model.response

import com.example.oreonconnection.data.users.UserData

data class UserStageInfo (val stageId: Int, val stageName: String, val stageLeadTime: Double)
data class UserInfo (val user: UserData, val stages: List<UserStageInfo>)
data class UserStages (val userInfo : UserInfo)