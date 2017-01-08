func = function(inparams) {
    var req = inparams.stack.req;
    var res = inparams.stack.res;
    var _ = inparams.require('underscore');
    var request = inparams.require('request');
    var async = inparams.require('async');
    var db = inparams.db;
    var query = req.query;

    var CONFIG = {
        apiKey: 'bb4a14e1ebf0493aa9a8e26d01dfb89f',
        baseUrl: 'http://api.football-data.org/v1/',
        typeSuffixes: {
            competitions: 'competitions/',
            teams: 'competitions/{id}/teams/',
            table: 'competitions/{id}/leagueTable/',
            fixtures: 'competitions/{id}/fixtures/',
            fixture: 'fixtures/{id}/',
            team: 'teams/{id}/',
            players: 'teams/{id}/players',
        }
    };

    urlSuffix = CONFIG.typeSuffixes[query.type];

    if (!urlSuffix) {
        return res.json(401, {
            error: 'Invalid type parameter', 
            message: _.keys(CONFIG.typeSuffixes)
        });
    }

    if (query.id) {
        urlSuffix = urlSuffix.replace('{id}', query.id);
    }

    async.auto({
        data: [function (next, args) {
            request({
                url: CONFIG.baseUrl + urlSuffix,
                method: 'GET',
                headers: {
                    'X-Auth-Token': CONFIG.apiKey
                },
                qs: query,
                json: true
            }, next);
        }],
        format: ['data', function(next, args) {
            var out = args.data[1];
            next(null, out);
        }],
    }, function (error, data) {
        if (error) return res.json(401, error);

        res.json(data.format);
    });
};
