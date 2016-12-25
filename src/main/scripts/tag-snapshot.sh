#/bin/bash

echo ${TRAVIS_BRANCH}
# Tag de snapshot. C'est un tag pour permettre le déploiement sur GitHUB.
if [ ${TRAVIS_BRANCH} = "snapshot" ]; then
	echo "Pas de reTag de snapshot";
else
	echo "ReTag de snapshot";
	git tag -d snapshot;
	git push https://$GITHUB_API_KEY@github.com/vzwingma/gestion-budget :refs/tags/snapshot;
	git tag snapshot;
	git push https://$GITHUB_API_KEY@github.com/vzwingma/gestion-budget --tags;
fi