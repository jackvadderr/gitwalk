package io.gitwalk.utils

import io.gitwalk.data.datasource.dto.AuthenticatedUserDTO
import io.gitwalk.data.datasource.dto.PlanDto
import io.gitwalk.domain.sdk.network.schemas.AuthenticatedUserSchema
import io.gitwalk.domain.sdk.network.schemas.PlanSchema

// Extensão para converter AuthenticatedUserSchema para AuthenticatedUserDTO
fun AuthenticatedUserSchema.toDto(): AuthenticatedUserDTO {
    return AuthenticatedUserDTO(
        login = this.login,
        id = this.id,
        node_id = this.node_id,
        avatar_url = this.avatar_url,
        gravatar_id = this.gravatar_id,
        url = this.url,
        html_url = this.html_url,
        followers_url = this.followers_url,
        following_url = this.following_url,
        gists_url = this.gists_url,
        starred_url = this.starred_url,
        subscriptions_url = this.subscriptions_url,
        organizations_url = this.organizations_url,
        repos_url = this.repos_url,
        events_url = this.events_url,
        received_events_url = this.received_events_url,
        type = this.type,
        site_admin = this.site_admin,
        name = this.name,
        company = this.company,
        blog = this.blog,
        location = this.location,
        email = this.email,
        hireable = this.hireable,
        bio = this.bio,
        twitter_username = this.twitter_username,
        public_repos = this.public_repos,
        public_gists = this.public_gists,
        followers = this.followers,
        following = this.following,
        created_at = this.created_at,
        updated_at = this.updated_at,
        private_gists = this.private_gists,
        total_private_repos = this.total_private_repos,
        owned_private_repos = this.owned_private_repos,
        disk_usage = this.disk_usage,
        collaborators = this.collaborators,
        two_factor_authentication = this.two_factor_authentication,
        plan = this.plan?.toDto()
    )
}

fun PlanDto.toSchema(): PlanSchema {
    return PlanSchema(
        name = this.name,
        space = this.space,
        private_repos = this.private_repos,
        collaborators = this.collaborators
    )
}

// Extensão para converter PlanSchema para PlanDto
fun PlanSchema.toDto(): PlanDto {
    return PlanDto(
        name = this.name,
        space = this.space,
        private_repos = this.private_repos,
        collaborators = this.collaborators
    )
}